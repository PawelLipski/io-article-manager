package dao

import models.copyright._
import scala.slick.driver.MySQLDriver.simple._
import utils.DatabaseSessionWrapper._
import slick.ojs.Tables._
import slick.ojs


object CopyrightTransferOjsDao {

  def getJournalIDForArticle(ojsArticleId: Int): Long = {

    withOjsDatabaseTransaction {

      implicit session => {
        Articles
          .filter(_.articleId === ojsArticleId.asInstanceOf[Long])
          .map(_.journalId)
          .first
      }
    }
  }

  def listArticlesByJournalAndVolume(ojsJournalId: Long, year: Int, volumeId: Int): Seq[Int] = {

      val yearFn = SimpleFunction[Int]("year")

      if(volumeId != -1) {
        withOjsDatabaseTransaction {
          implicit session =>
            (for {
              article <- ojs.Tables.Articles if
              article.journalId === ojsJournalId &&
              yearFn(Seq(article.lastModified)) === year
              issue <- ojs.Tables.PublishedArticles if issue.issueId === volumeId.toLong && article.journalId === ojsJournalId
            } yield article.articleId).run.map(_.asInstanceOf[Int])
        }
      } else {
        withOjsDatabaseTransaction {
          implicit session =>
            (for {
              article <- ojs.Tables.Articles if
              article.journalId === ojsJournalId &&
              yearFn(Seq(article.lastModified)) === year
            } yield article.articleId).run.map(_.asInstanceOf[Int])
        }
      }
  }

  def getCopyrightWrapperForArticle(ojsArticleId: Int): CopyrightWrapper = {

    withOjsDatabaseTransaction {

      implicit session =>

        val titleSettingValue = ArticleSettings
          .filter(_.articleId === ojsArticleId.asInstanceOf[Long])
          .filter(_.settingName === "title")
          .firstOption

        val title =
          if (titleSettingValue.isEmpty) ""
          else titleSettingValue.get.settingValue.getOrElse("")

        val copyright = Copyright.assemble(ojsArticleId, title, "")

        val authors = Authors
          .filter(_.submissionId === ojsArticleId.asInstanceOf[Long]) leftJoin AuthorSettings on (_.authorId === _.authorId)

        val correspondingAuthor = authors
          .filter(_._1.primaryContact =!= 0.asInstanceOf[Byte])
          .list
          .map(f => CorrespondingAuthor.assemble(
          f._1.firstName, f._1.middleName, f._1.lastName, f._2.settingValue.getOrElse(""), f._1.email))
          .head

        val contributionList = authors
          .list
          .map(f => Contribution.assemble(
          f._1.firstName, f._1.middleName, f._1.lastName, f._2.settingValue.getOrElse(""), "", 0))
          .toList

        CopyrightWrapper(copyright, correspondingAuthor, contributionList)
    }
  }

  def articleExists(id: Int): Boolean = {

    withOjsDatabaseTransaction {
      implicit session =>
        slick.ojs.Tables.ArticleSettings
          .filter(_.articleId === id.asInstanceOf[Long]).exists.run
    }
  }

}
