package models.copyright

case class CopyrightWrapper(
                                 copyright: Copyright,
                                 correspondingAuthor: CorrespondingAuthor,
                                 contributionList: List[Contribution]) {

}

object CopyrightWrapper {

  def build(tuple: (Int, String, String, CorrespondingAuthor, List[Contribution])): CopyrightWrapper = tuple match {
    case (ojsArticleId: Int, title: String, financialDisclosure: String, correspondingAuthor: CorrespondingAuthor, contributionList: List[Contribution]) =>
      CopyrightWrapper(Copyright(None, None, None, ojsArticleId, title, financialDisclosure), correspondingAuthor, contributionList)
  }

  def build(ojsArticleId: Int, title: String, financialDisclosure: String, correspondingAuthor: CorrespondingAuthor, contributionList: List[Contribution]): CopyrightWrapper = {
    build((ojsArticleId, title, financialDisclosure, correspondingAuthor, contributionList))
  }

  def unbuild(a: CopyrightWrapper): Option[(Int, String, String, CorrespondingAuthor, List[Contribution])] = {
    Some(a.copyright.ojsArticleId, a.copyright.title, a.copyright.financialDisclosure, a.correspondingAuthor, a.contributionList)
  }
}
