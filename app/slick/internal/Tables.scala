package slick.internal


// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.

  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Authors.ddl ++ Authorscontribution.ddl ++ Copyrighttransfer.ddl ++ InternalUsers.ddl
  
  /** Entity class storing rows of table Authors
   *  @param id Database column id PrimaryKey
   *  @param email Database column email  */
  case class AuthorsRow(id: Int, email: String)
  /** GetResult implicit for fetching AuthorsRow objects using plain SQL queries */
  implicit def GetResultAuthorsRow(implicit e0: GR[Int], e1: GR[String]): GR[AuthorsRow] = GR{
    prs => AuthorsRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table authors. Objects of this class serve as prototypes for rows in queries. */
  class Authors(tag: Tag) extends Table[AuthorsRow](tag, "authors") {
    def * = (id, email) <> (AuthorsRow.tupled, AuthorsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, email.?).shaped.<>({r=> _1.map(_=> AuthorsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column email  */
    val email: Column[String] = column[String]("email")
  }
  /** Collection-like TableQuery object for table Authors */
  lazy val Authors = new TableQuery(tag => new Authors(tag))
  
  /** Entity class storing rows of table Authorscontribution
   *  @param id Database column id AutoInc, PrimaryKey
   *  @param authorname Database column authorName 
   *  @param affiliation Database column affiliation 
   *  @param contribution Database column contribution 
   *  @param percent Database column percent 
   *  @param copyrighttransferid Database column CopyrightTransferID  */
  case class AuthorscontributionRow(id: Int, authorname: String, affiliation: String, contribution: String, percent: Int, copyrighttransferid: Int)
  /** GetResult implicit for fetching AuthorscontributionRow objects using plain SQL queries */
  implicit def GetResultAuthorscontributionRow(implicit e0: GR[Int], e1: GR[String]): GR[AuthorscontributionRow] = GR{
    prs => AuthorscontributionRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table AuthorsContribution. Objects of this class serve as prototypes for rows in queries. */
  class Authorscontribution(tag: Tag) extends Table[AuthorscontributionRow](tag, "AuthorsContribution") {
    def * = (id, authorname, affiliation, contribution, percent, copyrighttransferid) <> (AuthorscontributionRow.tupled, AuthorscontributionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, authorname.?, affiliation.?, contribution.?, percent.?, copyrighttransferid.?).shaped.<>({r=> _1.map(_=> AuthorscontributionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column authorName  */
    val authorname: Column[String] = column[String]("authorName")
    /** Database column affiliation  */
    val affiliation: Column[String] = column[String]("affiliation")
    /** Database column contribution  */
    val contribution: Column[String] = column[String]("contribution")
    /** Database column percent  */
    val percent: Column[Int] = column[Int]("percent")
    /** Database column CopyrightTransferID  */
    val copyrighttransferid: Column[Int] = column[Int]("CopyrightTransferID")
  }
  /** Collection-like TableQuery object for table Authorscontribution */
  lazy val Authorscontribution = new TableQuery(tag => new Authorscontribution(tag))
  
  /** Entity class storing rows of table Copyrighttransfer
   *  @param id Database column id AutoInc, PrimaryKey
   *  @param ojsarticleid Database column ojsArticleID 
   *  @param title Database column title 
   *  @param correspondingname Database column correspondingName 
   *  @param correspondingaffiliation Database column correspondingAffiliation 
   *  @param correspondingemail Database column correspondingEmail 
   *  @param dateformfilled Database column dateFormFilled 
   *  @param filleripaddress Database column fillerIpAddress 
   *  @param linktokenshasum Database column linkTokenSHASum 
   *  @param linkconfirmed Database column linkConfirmed 
   *  @param datelinkconfirmed Database column dateLinkConfirmed  */
  case class CopyrighttransferRow(id: Int, ojsarticleid: Int, title: String, correspondingname: String, correspondingaffiliation: String, correspondingemail: String, dateformfilled: java.sql.Date, filleripaddress: String, linktokenshasum: String, linkconfirmed: Boolean, datelinkconfirmed: java.sql.Date)
  /** GetResult implicit for fetching CopyrighttransferRow objects using plain SQL queries */
  implicit def GetResultCopyrighttransferRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Date], e3: GR[Boolean]): GR[CopyrighttransferRow] = GR{
    prs => CopyrighttransferRow.tupled((<<[Int], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Date], <<[String], <<[String], <<[Boolean], <<[java.sql.Date]))
  }
  /** Table description of table CopyrightTransfer. Objects of this class serve as prototypes for rows in queries. */
  class Copyrighttransfer(tag: Tag) extends Table[CopyrighttransferRow](tag, "CopyrightTransfer") {
    def * = (id, ojsarticleid, title, correspondingname, correspondingaffiliation, correspondingemail, dateformfilled, filleripaddress, linktokenshasum, linkconfirmed, datelinkconfirmed) <> (CopyrighttransferRow.tupled, CopyrighttransferRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, ojsarticleid.?, title.?, correspondingname.?, correspondingaffiliation.?, correspondingemail.?, dateformfilled.?, filleripaddress.?, linktokenshasum.?, linkconfirmed.?, datelinkconfirmed.?).shaped.<>({r=> _1.map(_=> CopyrighttransferRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column id AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column ojsArticleID  */
    val ojsarticleid: Column[Int] = column[Int]("ojsArticleID")
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column correspondingName  */
    val correspondingname: Column[String] = column[String]("correspondingName")
    /** Database column correspondingAffiliation  */
    val correspondingaffiliation: Column[String] = column[String]("correspondingAffiliation")
    /** Database column correspondingEmail  */
    val correspondingemail: Column[String] = column[String]("correspondingEmail")
    /** Database column dateFormFilled  */
    val dateformfilled: Column[java.sql.Date] = column[java.sql.Date]("dateFormFilled")
    /** Database column fillerIpAddress  */
    val filleripaddress: Column[String] = column[String]("fillerIpAddress")
    /** Database column linkTokenSHASum  */
    val linktokenshasum: Column[String] = column[String]("linkTokenSHASum")
    /** Database column linkConfirmed  */
    val linkconfirmed: Column[Boolean] = column[Boolean]("linkConfirmed")
    /** Database column dateLinkConfirmed  */
    val datelinkconfirmed: Column[java.sql.Date] = column[java.sql.Date]("dateLinkConfirmed")
  }
  /** Collection-like TableQuery object for table Copyrighttransfer */
  lazy val Copyrighttransfer = new TableQuery(tag => new Copyrighttransfer(tag))
  
  /** Entity class storing rows of table InternalUsers
   *  @param id Database column ID PrimaryKey
   *  @param name Database column NAME 
   *  @param passwordSha1Sum Database column PASSWORD_SHA1_SUM  */
  case class InternalUsersRow(id: Int, name: String, passwordSha1Sum: String)
  /** GetResult implicit for fetching InternalUsersRow objects using plain SQL queries */
  implicit def GetResultInternalUsersRow(implicit e0: GR[Int], e1: GR[String]): GR[InternalUsersRow] = GR{
    prs => InternalUsersRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table INTERNAL_USERS. Objects of this class serve as prototypes for rows in queries. */
  class InternalUsers(tag: Tag) extends Table[InternalUsersRow](tag, "INTERNAL_USERS") {
    def * = (id, name, passwordSha1Sum) <> (InternalUsersRow.tupled, InternalUsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name.?, passwordSha1Sum.?).shaped.<>({r=> _1.map(_=> InternalUsersRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column ID PrimaryKey */
    val id: Column[Int] = column[Int]("ID", O.PrimaryKey)
    /** Database column NAME  */
    val name: Column[String] = column[String]("NAME")
    /** Database column PASSWORD_SHA1_SUM  */
    val passwordSha1Sum: Column[String] = column[String]("PASSWORD_SHA1_SUM")
  }
  /** Collection-like TableQuery object for table InternalUsers */
  lazy val InternalUsers = new TableQuery(tag => new InternalUsers(tag))
}