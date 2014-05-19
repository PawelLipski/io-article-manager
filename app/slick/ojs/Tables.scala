package slick.ojs

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
  lazy val ddl = AccessKeys.ddl ++ Announcements.ddl ++ AnnouncementSettings.ddl ++ AnnouncementTypes.ddl ++ AnnouncementTypeSettings.ddl ++ ArticleComments.ddl ++ ArticleEmailLog.ddl ++ ArticleEventLog.ddl ++ ArticleFiles.ddl ++ ArticleGalleys.ddl ++ ArticleHtmlGalleyImages.ddl ++ ArticleNotes.ddl ++ Articles.ddl ++ ArticleSearchKeywordList.ddl ++ ArticleSearchObjectKeywords.ddl ++ ArticleSearchObjects.ddl ++ ArticleSettings.ddl ++ ArticleSuppFileSettings.ddl ++ ArticleSupplementaryFiles.ddl ++ ArticleXmlGalleys.ddl ++ Authors.ddl ++ AuthorSettings.ddl ++ AuthSources.ddl ++ BooksForReview.ddl ++ BooksForReviewAuthors.ddl ++ BooksForReviewSettings.ddl ++ Captchas.ddl ++ Citations.ddl ++ CitationSettings.ddl ++ Comments.ddl ++ CompletedPayments.ddl ++ ControlledVocabEntries.ddl ++ ControlledVocabEntrySettings.ddl ++ ControlledVocabs.ddl ++ CounterMonthlyLog.ddl ++ CustomIssueOrders.ddl ++ CustomSectionOrders.ddl ++ EditAssignments.ddl ++ EditDecisions.ddl ++ EmailTemplates.ddl ++ EmailTemplatesData.ddl ++ EmailTemplatesDefault.ddl ++ EmailTemplatesDefaultData.ddl ++ ExternalFeeds.ddl ++ ExternalFeedSettings.ddl ++ Filters.ddl ++ FilterSettings.ddl ++ GroupMemberships.ddl ++ Groups.ddl ++ GroupSettings.ddl ++ InstitutionalSubscriptionIp.ddl ++ InstitutionalSubscriptions.ddl ++ Issues.ddl ++ IssueSettings.ddl ++ Journals.ddl ++ JournalSettings.ddl ++ MetadataDescriptions.ddl ++ MetadataDescriptionSettings.ddl ++ Notes.ddl ++ Notifications.ddl ++ NotificationSettings.ddl ++ OaiResumptionTokens.ddl ++ PaypalTransactions.ddl ++ PluginSettings.ddl ++ Processes.ddl ++ PublishedArticles.ddl ++ QueuedPayments.ddl ++ Referrals.ddl ++ ReferralSettings.ddl ++ ReviewAssignments.ddl ++ ReviewFormElements.ddl ++ ReviewFormElementSettings.ddl ++ ReviewFormResponses.ddl ++ ReviewForms.ddl ++ ReviewFormSettings.ddl ++ ReviewRounds.ddl ++ Roles.ddl ++ RtContexts.ddl ++ RtSearches.ddl ++ RtVersions.ddl ++ ScheduledTasks.ddl ++ SectionEditors.ddl ++ Sections.ddl ++ SectionSettings.ddl ++ Sessions.ddl ++ Signoffs.ddl ++ Site.ddl ++ SiteSettings.ddl ++ StaticPages.ddl ++ StaticPageSettings.ddl ++ Subscriptions.ddl ++ SubscriptionTypes.ddl ++ SubscriptionTypeSettings.ddl ++ TemporaryFiles.ddl ++ Theses.ddl ++ UserInterests.ddl ++ Users.ddl ++ UserSettings.ddl ++ Versions.ddl
  
  /** Entity class storing rows of table AccessKeys
   *  @param accessKeyId Database column access_key_id AutoInc, PrimaryKey
   *  @param context Database column context 
   *  @param keyHash Database column key_hash 
   *  @param userId Database column user_id 
   *  @param assocId Database column assoc_id 
   *  @param expiryDate Database column expiry_date  */
  case class AccessKeysRow(accessKeyId: Long, context: String, keyHash: String, userId: Long, assocId: Option[Long], expiryDate: java.sql.Timestamp)
  /** GetResult implicit for fetching AccessKeysRow objects using plain SQL queries */
  implicit def GetResultAccessKeysRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]], e3: GR[java.sql.Timestamp]): GR[AccessKeysRow] = GR{
    prs => AccessKeysRow.tupled((<<[Long], <<[String], <<[String], <<[Long], <<?[Long], <<[java.sql.Timestamp]))
  }
  /** Table description of table access_keys. Objects of this class serve as prototypes for rows in queries. */
  class AccessKeys(tag: Tag) extends Table[AccessKeysRow](tag, "access_keys") {
    def * = (accessKeyId, context, keyHash, userId, assocId, expiryDate) <> (AccessKeysRow.tupled, AccessKeysRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (accessKeyId.?, context.?, keyHash.?, userId.?, assocId, expiryDate.?).shaped.<>({r=> _1.map(_=> AccessKeysRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column access_key_id AutoInc, PrimaryKey */
    val accessKeyId: Column[Long] = column[Long]("access_key_id", O.AutoInc, O.PrimaryKey)
    /** Database column context  */
    val context: Column[String] = column[String]("context")
    /** Database column key_hash  */
    val keyHash: Column[String] = column[String]("key_hash")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    /** Database column expiry_date  */
    val expiryDate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("expiry_date")
    
    /** Index over (keyHash,userId,context) (database name access_keys_hash) */
    val index1 = index("access_keys_hash", (keyHash, userId, context))
  }
  /** Collection-like TableQuery object for table AccessKeys */
  lazy val AccessKeys = new TableQuery(tag => new AccessKeys(tag))
  
  /** Entity class storing rows of table Announcements
   *  @param announcementId Database column announcement_id AutoInc, PrimaryKey
   *  @param assocType Database column assoc_type 
   *  @param assocId Database column assoc_id 
   *  @param typeId Database column type_id 
   *  @param dateExpire Database column date_expire 
   *  @param datePosted Database column date_posted  */
  case class AnnouncementsRow(announcementId: Long, assocType: Option[Short], assocId: Long, typeId: Option[Long], dateExpire: Option[java.sql.Timestamp], datePosted: java.sql.Timestamp)
  /** GetResult implicit for fetching AnnouncementsRow objects using plain SQL queries */
  implicit def GetResultAnnouncementsRow(implicit e0: GR[Long], e1: GR[Option[Short]], e2: GR[Option[Long]], e3: GR[Option[java.sql.Timestamp]], e4: GR[java.sql.Timestamp]): GR[AnnouncementsRow] = GR{
    prs => AnnouncementsRow.tupled((<<[Long], <<?[Short], <<[Long], <<?[Long], <<?[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table announcements. Objects of this class serve as prototypes for rows in queries. */
  class Announcements(tag: Tag) extends Table[AnnouncementsRow](tag, "announcements") {
    def * = (announcementId, assocType, assocId, typeId, dateExpire, datePosted) <> (AnnouncementsRow.tupled, AnnouncementsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (announcementId.?, assocType, assocId.?, typeId, dateExpire, datePosted.?).shaped.<>({r=> _1.map(_=> AnnouncementsRow.tupled((_1.get, _2, _3.get, _4, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column announcement_id AutoInc, PrimaryKey */
    val announcementId: Column[Long] = column[Long]("announcement_id", O.AutoInc, O.PrimaryKey)
    /** Database column assoc_type  */
    val assocType: Column[Option[Short]] = column[Option[Short]]("assoc_type")
    /** Database column assoc_id  */
    val assocId: Column[Long] = column[Long]("assoc_id")
    /** Database column type_id  */
    val typeId: Column[Option[Long]] = column[Option[Long]]("type_id")
    /** Database column date_expire  */
    val dateExpire: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_expire")
    /** Database column date_posted  */
    val datePosted: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_posted")
    
    /** Index over (assocType,assocId) (database name announcements_assoc) */
    val index1 = index("announcements_assoc", (assocType, assocId))
  }
  /** Collection-like TableQuery object for table Announcements */
  lazy val Announcements = new TableQuery(tag => new Announcements(tag))
  
  /** Entity class storing rows of table AnnouncementSettings
   *  @param announcementId Database column announcement_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class AnnouncementSettingsRow(announcementId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching AnnouncementSettingsRow objects using plain SQL queries */
  implicit def GetResultAnnouncementSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[AnnouncementSettingsRow] = GR{
    prs => AnnouncementSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table announcement_settings. Objects of this class serve as prototypes for rows in queries. */
  class AnnouncementSettings(tag: Tag) extends Table[AnnouncementSettingsRow](tag, "announcement_settings") {
    def * = (announcementId, locale, settingName, settingValue, settingType) <> (AnnouncementSettingsRow.tupled, AnnouncementSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (announcementId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> AnnouncementSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column announcement_id  */
    val announcementId: Column[Long] = column[Long]("announcement_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (announcementId) (database name announcement_settings_announcement_id) */
    val index1 = index("announcement_settings_announcement_id", announcementId)
    /** Uniqueness Index over (announcementId,locale,settingName) (database name announcement_settings_pkey) */
    val index2 = index("announcement_settings_pkey", (announcementId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table AnnouncementSettings */
  lazy val AnnouncementSettings = new TableQuery(tag => new AnnouncementSettings(tag))
  
  /** Entity class storing rows of table AnnouncementTypes
   *  @param typeId Database column type_id AutoInc, PrimaryKey
   *  @param assocType Database column assoc_type 
   *  @param assocId Database column assoc_id  */
  case class AnnouncementTypesRow(typeId: Long, assocType: Option[Short], assocId: Long)
  /** GetResult implicit for fetching AnnouncementTypesRow objects using plain SQL queries */
  implicit def GetResultAnnouncementTypesRow(implicit e0: GR[Long], e1: GR[Option[Short]]): GR[AnnouncementTypesRow] = GR{
    prs => AnnouncementTypesRow.tupled((<<[Long], <<?[Short], <<[Long]))
  }
  /** Table description of table announcement_types. Objects of this class serve as prototypes for rows in queries. */
  class AnnouncementTypes(tag: Tag) extends Table[AnnouncementTypesRow](tag, "announcement_types") {
    def * = (typeId, assocType, assocId) <> (AnnouncementTypesRow.tupled, AnnouncementTypesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (typeId.?, assocType, assocId.?).shaped.<>({r=> _1.map(_=> AnnouncementTypesRow.tupled((_1.get, _2, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column type_id AutoInc, PrimaryKey */
    val typeId: Column[Long] = column[Long]("type_id", O.AutoInc, O.PrimaryKey)
    /** Database column assoc_type  */
    val assocType: Column[Option[Short]] = column[Option[Short]]("assoc_type")
    /** Database column assoc_id  */
    val assocId: Column[Long] = column[Long]("assoc_id")
    
    /** Index over (assocType,assocId) (database name announcement_types_assoc) */
    val index1 = index("announcement_types_assoc", (assocType, assocId))
  }
  /** Collection-like TableQuery object for table AnnouncementTypes */
  lazy val AnnouncementTypes = new TableQuery(tag => new AnnouncementTypes(tag))
  
  /** Entity class storing rows of table AnnouncementTypeSettings
   *  @param typeId Database column type_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class AnnouncementTypeSettingsRow(typeId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching AnnouncementTypeSettingsRow objects using plain SQL queries */
  implicit def GetResultAnnouncementTypeSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[AnnouncementTypeSettingsRow] = GR{
    prs => AnnouncementTypeSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table announcement_type_settings. Objects of this class serve as prototypes for rows in queries. */
  class AnnouncementTypeSettings(tag: Tag) extends Table[AnnouncementTypeSettingsRow](tag, "announcement_type_settings") {
    def * = (typeId, locale, settingName, settingValue, settingType) <> (AnnouncementTypeSettingsRow.tupled, AnnouncementTypeSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (typeId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> AnnouncementTypeSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column type_id  */
    val typeId: Column[Long] = column[Long]("type_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (typeId,locale,settingName) (database name announcement_type_settings_pkey) */
    val index1 = index("announcement_type_settings_pkey", (typeId, locale, settingName), unique=true)
    /** Index over (typeId) (database name announcement_type_settings_type_id) */
    val index2 = index("announcement_type_settings_type_id", typeId)
  }
  /** Collection-like TableQuery object for table AnnouncementTypeSettings */
  lazy val AnnouncementTypeSettings = new TableQuery(tag => new AnnouncementTypeSettings(tag))
  
  /** Entity class storing rows of table ArticleComments
   *  @param commentId Database column comment_id AutoInc, PrimaryKey
   *  @param commentType Database column comment_type 
   *  @param roleId Database column role_id 
   *  @param articleId Database column article_id 
   *  @param assocId Database column assoc_id 
   *  @param authorId Database column author_id 
   *  @param commentTitle Database column comment_title 
   *  @param comments Database column comments 
   *  @param datePosted Database column date_posted 
   *  @param dateModified Database column date_modified 
   *  @param viewable Database column viewable  */
  case class ArticleCommentsRow(commentId: Long, commentType: Option[Long], roleId: Long, articleId: Long, assocId: Long, authorId: Long, commentTitle: String, comments: Option[String], datePosted: Option[java.sql.Timestamp], dateModified: Option[java.sql.Timestamp], viewable: Option[Byte])
  /** GetResult implicit for fetching ArticleCommentsRow objects using plain SQL queries */
  implicit def GetResultArticleCommentsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[String], e3: GR[Option[String]], e4: GR[Option[java.sql.Timestamp]], e5: GR[Option[Byte]]): GR[ArticleCommentsRow] = GR{
    prs => ArticleCommentsRow.tupled((<<[Long], <<?[Long], <<[Long], <<[Long], <<[Long], <<[Long], <<[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[Byte]))
  }
  /** Table description of table article_comments. Objects of this class serve as prototypes for rows in queries. */
  class ArticleComments(tag: Tag) extends Table[ArticleCommentsRow](tag, "article_comments") {
    def * = (commentId, commentType, roleId, articleId, assocId, authorId, commentTitle, comments, datePosted, dateModified, viewable) <> (ArticleCommentsRow.tupled, ArticleCommentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (commentId.?, commentType, roleId.?, articleId.?, assocId.?, authorId.?, commentTitle.?, comments, datePosted, dateModified, viewable).shaped.<>({r=> _1.map(_=> ArticleCommentsRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6.get, _7.get, _8, _9, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column comment_id AutoInc, PrimaryKey */
    val commentId: Column[Long] = column[Long]("comment_id", O.AutoInc, O.PrimaryKey)
    /** Database column comment_type  */
    val commentType: Column[Option[Long]] = column[Option[Long]]("comment_type")
    /** Database column role_id  */
    val roleId: Column[Long] = column[Long]("role_id")
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column assoc_id  */
    val assocId: Column[Long] = column[Long]("assoc_id")
    /** Database column author_id  */
    val authorId: Column[Long] = column[Long]("author_id")
    /** Database column comment_title  */
    val commentTitle: Column[String] = column[String]("comment_title")
    /** Database column comments  */
    val comments: Column[Option[String]] = column[Option[String]]("comments")
    /** Database column date_posted  */
    val datePosted: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_posted")
    /** Database column date_modified  */
    val dateModified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_modified")
    /** Database column viewable  */
    val viewable: Column[Option[Byte]] = column[Option[Byte]]("viewable")
    
    /** Index over (articleId) (database name article_comments_article_id) */
    val index1 = index("article_comments_article_id", articleId)
  }
  /** Collection-like TableQuery object for table ArticleComments */
  lazy val ArticleComments = new TableQuery(tag => new ArticleComments(tag))
  
  /** Entity class storing rows of table ArticleEmailLog
   *  @param logId Database column log_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param senderId Database column sender_id 
   *  @param dateSent Database column date_sent 
   *  @param ipAddress Database column ip_address 
   *  @param eventType Database column event_type 
   *  @param assocType Database column assoc_type 
   *  @param assocId Database column assoc_id 
   *  @param fromAddress Database column from_address 
   *  @param recipients Database column recipients 
   *  @param ccRecipients Database column cc_recipients 
   *  @param bccRecipients Database column bcc_recipients 
   *  @param subject Database column subject 
   *  @param body Database column body  */
  case class ArticleEmailLogRow(logId: Long, articleId: Long, senderId: Long, dateSent: java.sql.Timestamp, ipAddress: Option[String], eventType: Option[Long], assocType: Option[Long], assocId: Option[Long], fromAddress: Option[String], recipients: Option[String], ccRecipients: Option[String], bccRecipients: Option[String], subject: Option[String], body: Option[String])
  /** GetResult implicit for fetching ArticleEmailLogRow objects using plain SQL queries */
  implicit def GetResultArticleEmailLogRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp], e2: GR[Option[String]], e3: GR[Option[Long]]): GR[ArticleEmailLogRow] = GR{
    prs => ArticleEmailLogRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<?[String], <<?[Long], <<?[Long], <<?[Long], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table article_email_log. Objects of this class serve as prototypes for rows in queries. */
  class ArticleEmailLog(tag: Tag) extends Table[ArticleEmailLogRow](tag, "article_email_log") {
    def * = (logId, articleId, senderId, dateSent, ipAddress, eventType, assocType, assocId, fromAddress, recipients, ccRecipients, bccRecipients, subject, body) <> (ArticleEmailLogRow.tupled, ArticleEmailLogRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (logId.?, articleId.?, senderId.?, dateSent.?, ipAddress, eventType, assocType, assocId, fromAddress, recipients, ccRecipients, bccRecipients, subject, body).shaped.<>({r=> _1.map(_=> ArticleEmailLogRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column log_id AutoInc, PrimaryKey */
    val logId: Column[Long] = column[Long]("log_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column sender_id  */
    val senderId: Column[Long] = column[Long]("sender_id")
    /** Database column date_sent  */
    val dateSent: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_sent")
    /** Database column ip_address  */
    val ipAddress: Column[Option[String]] = column[Option[String]]("ip_address")
    /** Database column event_type  */
    val eventType: Column[Option[Long]] = column[Option[Long]]("event_type")
    /** Database column assoc_type  */
    val assocType: Column[Option[Long]] = column[Option[Long]]("assoc_type")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    /** Database column from_address  */
    val fromAddress: Column[Option[String]] = column[Option[String]]("from_address")
    /** Database column recipients  */
    val recipients: Column[Option[String]] = column[Option[String]]("recipients")
    /** Database column cc_recipients  */
    val ccRecipients: Column[Option[String]] = column[Option[String]]("cc_recipients")
    /** Database column bcc_recipients  */
    val bccRecipients: Column[Option[String]] = column[Option[String]]("bcc_recipients")
    /** Database column subject  */
    val subject: Column[Option[String]] = column[Option[String]]("subject")
    /** Database column body  */
    val body: Column[Option[String]] = column[Option[String]]("body")
    
    /** Index over (articleId) (database name article_email_log_article_id) */
    val index1 = index("article_email_log_article_id", articleId)
  }
  /** Collection-like TableQuery object for table ArticleEmailLog */
  lazy val ArticleEmailLog = new TableQuery(tag => new ArticleEmailLog(tag))
  
  /** Entity class storing rows of table ArticleEventLog
   *  @param logId Database column log_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param userId Database column user_id 
   *  @param dateLogged Database column date_logged 
   *  @param ipAddress Database column ip_address 
   *  @param logLevel Database column log_level 
   *  @param eventType Database column event_type 
   *  @param assocType Database column assoc_type 
   *  @param assocId Database column assoc_id 
   *  @param message Database column message  */
  case class ArticleEventLogRow(logId: Long, articleId: Long, userId: Long, dateLogged: java.sql.Timestamp, ipAddress: String, logLevel: Option[String], eventType: Option[Long], assocType: Option[Long], assocId: Option[Long], message: Option[String])
  /** GetResult implicit for fetching ArticleEventLogRow objects using plain SQL queries */
  implicit def GetResultArticleEventLogRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp], e2: GR[String], e3: GR[Option[String]], e4: GR[Option[Long]]): GR[ArticleEventLogRow] = GR{
    prs => ArticleEventLogRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[String], <<?[String], <<?[Long], <<?[Long], <<?[Long], <<?[String]))
  }
  /** Table description of table article_event_log. Objects of this class serve as prototypes for rows in queries. */
  class ArticleEventLog(tag: Tag) extends Table[ArticleEventLogRow](tag, "article_event_log") {
    def * = (logId, articleId, userId, dateLogged, ipAddress, logLevel, eventType, assocType, assocId, message) <> (ArticleEventLogRow.tupled, ArticleEventLogRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (logId.?, articleId.?, userId.?, dateLogged.?, ipAddress.?, logLevel, eventType, assocType, assocId, message).shaped.<>({r=> _1.map(_=> ArticleEventLogRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column log_id AutoInc, PrimaryKey */
    val logId: Column[Long] = column[Long]("log_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column date_logged  */
    val dateLogged: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_logged")
    /** Database column ip_address  */
    val ipAddress: Column[String] = column[String]("ip_address")
    /** Database column log_level  */
    val logLevel: Column[Option[String]] = column[Option[String]]("log_level")
    /** Database column event_type  */
    val eventType: Column[Option[Long]] = column[Option[Long]]("event_type")
    /** Database column assoc_type  */
    val assocType: Column[Option[Long]] = column[Option[Long]]("assoc_type")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    /** Database column message  */
    val message: Column[Option[String]] = column[Option[String]]("message")
    
    /** Index over (articleId) (database name article_event_log_article_id) */
    val index1 = index("article_event_log_article_id", articleId)
  }
  /** Collection-like TableQuery object for table ArticleEventLog */
  lazy val ArticleEventLog = new TableQuery(tag => new ArticleEventLog(tag))
  
  /** Entity class storing rows of table ArticleFiles
   *  @param fileId Database column file_id AutoInc
   *  @param revision Database column revision 
   *  @param sourceFileId Database column source_file_id 
   *  @param sourceRevision Database column source_revision 
   *  @param articleId Database column article_id 
   *  @param fileName Database column file_name 
   *  @param fileType Database column file_type 
   *  @param fileSize Database column file_size 
   *  @param originalFileName Database column original_file_name 
   *  @param `type` Database column type 
   *  @param viewable Database column viewable 
   *  @param dateUploaded Database column date_uploaded 
   *  @param dateModified Database column date_modified 
   *  @param round Database column round 
   *  @param assocId Database column assoc_id  */
  case class ArticleFilesRow(fileId: Long, revision: Long, sourceFileId: Option[Long], sourceRevision: Option[Long], articleId: Long, fileName: String, fileType: String, fileSize: Long, originalFileName: Option[String], `type`: String, viewable: Option[Byte], dateUploaded: java.sql.Timestamp, dateModified: java.sql.Timestamp, round: Byte, assocId: Option[Long])
  /** GetResult implicit for fetching ArticleFilesRow objects using plain SQL queries */
  implicit def GetResultArticleFilesRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[String], e3: GR[Option[String]], e4: GR[Option[Byte]], e5: GR[java.sql.Timestamp], e6: GR[Byte]): GR[ArticleFilesRow] = GR{
    prs => ArticleFilesRow.tupled((<<[Long], <<[Long], <<?[Long], <<?[Long], <<[Long], <<[String], <<[String], <<[Long], <<?[String], <<[String], <<?[Byte], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Byte], <<?[Long]))
  }
  /** Table description of table article_files. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class ArticleFiles(tag: Tag) extends Table[ArticleFilesRow](tag, "article_files") {
    def * = (fileId, revision, sourceFileId, sourceRevision, articleId, fileName, fileType, fileSize, originalFileName, `type`, viewable, dateUploaded, dateModified, round, assocId) <> (ArticleFilesRow.tupled, ArticleFilesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (fileId.?, revision.?, sourceFileId, sourceRevision, articleId.?, fileName.?, fileType.?, fileSize.?, originalFileName, `type`.?, viewable, dateUploaded.?, dateModified.?, round.?, assocId).shaped.<>({r=> _1.map(_=> ArticleFilesRow.tupled((_1.get, _2.get, _3, _4, _5.get, _6.get, _7.get, _8.get, _9, _10.get, _11, _12.get, _13.get, _14.get, _15)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column file_id AutoInc */
    val fileId: Column[Long] = column[Long]("file_id", O.AutoInc)
    /** Database column revision  */
    val revision: Column[Long] = column[Long]("revision")
    /** Database column source_file_id  */
    val sourceFileId: Column[Option[Long]] = column[Option[Long]]("source_file_id")
    /** Database column source_revision  */
    val sourceRevision: Column[Option[Long]] = column[Option[Long]]("source_revision")
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column file_name  */
    val fileName: Column[String] = column[String]("file_name")
    /** Database column file_type  */
    val fileType: Column[String] = column[String]("file_type")
    /** Database column file_size  */
    val fileSize: Column[Long] = column[Long]("file_size")
    /** Database column original_file_name  */
    val originalFileName: Column[Option[String]] = column[Option[String]]("original_file_name")
    /** Database column type 
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Column[String] = column[String]("type")
    /** Database column viewable  */
    val viewable: Column[Option[Byte]] = column[Option[Byte]]("viewable")
    /** Database column date_uploaded  */
    val dateUploaded: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_uploaded")
    /** Database column date_modified  */
    val dateModified: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_modified")
    /** Database column round  */
    val round: Column[Byte] = column[Byte]("round")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    
    /** Primary key of ArticleFiles (database name article_files_PK) */
    val pk = primaryKey("article_files_PK", (fileId, revision))
    
    /** Index over (articleId) (database name article_files_article_id) */
    val index1 = index("article_files_article_id", articleId)
  }
  /** Collection-like TableQuery object for table ArticleFiles */
  lazy val ArticleFiles = new TableQuery(tag => new ArticleFiles(tag))
  
  /** Entity class storing rows of table ArticleGalleys
   *  @param galleyId Database column galley_id AutoInc, PrimaryKey
   *  @param publicGalleyId Database column public_galley_id 
   *  @param locale Database column locale 
   *  @param articleId Database column article_id 
   *  @param fileId Database column file_id 
   *  @param label Database column label 
   *  @param htmlGalley Database column html_galley 
   *  @param styleFileId Database column style_file_id 
   *  @param seq Database column seq Default(0.0)
   *  @param views Database column views Default(0) */
  case class ArticleGalleysRow(galleyId: Long, publicGalleyId: Option[String], locale: Option[String], articleId: Long, fileId: Long, label: Option[String], htmlGalley: Byte, styleFileId: Option[Long], seq: Double = 0.0, views: Int = 0)
  /** GetResult implicit for fetching ArticleGalleysRow objects using plain SQL queries */
  implicit def GetResultArticleGalleysRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Byte], e3: GR[Option[Long]], e4: GR[Double], e5: GR[Int]): GR[ArticleGalleysRow] = GR{
    prs => ArticleGalleysRow.tupled((<<[Long], <<?[String], <<?[String], <<[Long], <<[Long], <<?[String], <<[Byte], <<?[Long], <<[Double], <<[Int]))
  }
  /** Table description of table article_galleys. Objects of this class serve as prototypes for rows in queries. */
  class ArticleGalleys(tag: Tag) extends Table[ArticleGalleysRow](tag, "article_galleys") {
    def * = (galleyId, publicGalleyId, locale, articleId, fileId, label, htmlGalley, styleFileId, seq, views) <> (ArticleGalleysRow.tupled, ArticleGalleysRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (galleyId.?, publicGalleyId, locale, articleId.?, fileId.?, label, htmlGalley.?, styleFileId, seq.?, views.?).shaped.<>({r=> _1.map(_=> ArticleGalleysRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6, _7.get, _8, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column galley_id AutoInc, PrimaryKey */
    val galleyId: Column[Long] = column[Long]("galley_id", O.AutoInc, O.PrimaryKey)
    /** Database column public_galley_id  */
    val publicGalleyId: Column[Option[String]] = column[Option[String]]("public_galley_id")
    /** Database column locale  */
    val locale: Column[Option[String]] = column[Option[String]]("locale")
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column file_id  */
    val fileId: Column[Long] = column[Long]("file_id")
    /** Database column label  */
    val label: Column[Option[String]] = column[Option[String]]("label")
    /** Database column html_galley  */
    val htmlGalley: Column[Byte] = column[Byte]("html_galley")
    /** Database column style_file_id  */
    val styleFileId: Column[Option[Long]] = column[Option[Long]]("style_file_id")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    /** Database column views Default(0) */
    val views: Column[Int] = column[Int]("views", O.Default(0))
    
    /** Index over (articleId) (database name article_galleys_article_id) */
    val index1 = index("article_galleys_article_id", articleId)
    /** Uniqueness Index over (publicGalleyId,articleId) (database name article_galleys_public_id) */
    val index2 = index("article_galleys_public_id", (publicGalleyId, articleId), unique=true)
  }
  /** Collection-like TableQuery object for table ArticleGalleys */
  lazy val ArticleGalleys = new TableQuery(tag => new ArticleGalleys(tag))
  
  /** Entity class storing rows of table ArticleHtmlGalleyImages
   *  @param galleyId Database column galley_id 
   *  @param fileId Database column file_id  */
  case class ArticleHtmlGalleyImagesRow(galleyId: Long, fileId: Long)
  /** GetResult implicit for fetching ArticleHtmlGalleyImagesRow objects using plain SQL queries */
  implicit def GetResultArticleHtmlGalleyImagesRow(implicit e0: GR[Long]): GR[ArticleHtmlGalleyImagesRow] = GR{
    prs => ArticleHtmlGalleyImagesRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table article_html_galley_images. Objects of this class serve as prototypes for rows in queries. */
  class ArticleHtmlGalleyImages(tag: Tag) extends Table[ArticleHtmlGalleyImagesRow](tag, "article_html_galley_images") {
    def * = (galleyId, fileId) <> (ArticleHtmlGalleyImagesRow.tupled, ArticleHtmlGalleyImagesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (galleyId.?, fileId.?).shaped.<>({r=> _1.map(_=> ArticleHtmlGalleyImagesRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column galley_id  */
    val galleyId: Column[Long] = column[Long]("galley_id")
    /** Database column file_id  */
    val fileId: Column[Long] = column[Long]("file_id")
    
    /** Uniqueness Index over (galleyId,fileId) (database name article_html_galley_images_pkey) */
    val index1 = index("article_html_galley_images_pkey", (galleyId, fileId), unique=true)
  }
  /** Collection-like TableQuery object for table ArticleHtmlGalleyImages */
  lazy val ArticleHtmlGalleyImages = new TableQuery(tag => new ArticleHtmlGalleyImages(tag))
  
  /** Entity class storing rows of table ArticleNotes
   *  @param noteId Database column note_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param userId Database column user_id 
   *  @param dateCreated Database column date_created 
   *  @param dateModified Database column date_modified 
   *  @param title Database column title 
   *  @param note Database column note 
   *  @param fileId Database column file_id  */
  case class ArticleNotesRow(noteId: Long, articleId: Long, userId: Long, dateCreated: java.sql.Timestamp, dateModified: java.sql.Timestamp, title: String, note: Option[String], fileId: Long)
  /** GetResult implicit for fetching ArticleNotesRow objects using plain SQL queries */
  implicit def GetResultArticleNotesRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp], e2: GR[String], e3: GR[Option[String]]): GR[ArticleNotesRow] = GR{
    prs => ArticleNotesRow.tupled((<<[Long], <<[Long], <<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[String], <<?[String], <<[Long]))
  }
  /** Table description of table article_notes. Objects of this class serve as prototypes for rows in queries. */
  class ArticleNotes(tag: Tag) extends Table[ArticleNotesRow](tag, "article_notes") {
    def * = (noteId, articleId, userId, dateCreated, dateModified, title, note, fileId) <> (ArticleNotesRow.tupled, ArticleNotesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (noteId.?, articleId.?, userId.?, dateCreated.?, dateModified.?, title.?, note, fileId.?).shaped.<>({r=> _1.map(_=> ArticleNotesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column note_id AutoInc, PrimaryKey */
    val noteId: Column[Long] = column[Long]("note_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column date_created  */
    val dateCreated: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_created")
    /** Database column date_modified  */
    val dateModified: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_modified")
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column note  */
    val note: Column[Option[String]] = column[Option[String]]("note")
    /** Database column file_id  */
    val fileId: Column[Long] = column[Long]("file_id")
    
    /** Index over (articleId) (database name article_notes_article_id) */
    val index1 = index("article_notes_article_id", articleId)
    /** Index over (fileId) (database name article_notes_file_id) */
    val index2 = index("article_notes_file_id", fileId)
    /** Index over (userId) (database name article_notes_user_id) */
    val index3 = index("article_notes_user_id", userId)
  }
  /** Collection-like TableQuery object for table ArticleNotes */
  lazy val ArticleNotes = new TableQuery(tag => new ArticleNotes(tag))
  
  /** Row type of table Articles */
  type ArticlesRow = HCons[Long,HCons[Option[String],HCons[Long,HCons[Long,HCons[Option[Long],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Byte,HCons[Byte,HCons[Byte,HCons[Option[Long],HCons[Option[Long],HCons[Option[Long],HCons[Option[Long],HCons[Option[String],HCons[Option[String],HCons[Byte,HCons[Byte,HCons[Byte,HNil]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for ArticlesRow providing default values if available in the database schema. */
  def ArticlesRow(articleId: Long, locale: Option[String], userId: Long, journalId: Long, sectionId: Option[Long], language: Option[String] = None, commentsToEd: Option[String], citations: Option[String], dateSubmitted: Option[java.sql.Timestamp], lastModified: Option[java.sql.Timestamp], dateStatusModified: Option[java.sql.Timestamp], status: Byte, submissionProgress: Byte, currentRound: Byte, submissionFileId: Option[Long], revisedFileId: Option[Long], reviewFileId: Option[Long], editorFileId: Option[Long], pages: Option[String], doi: Option[String], fastTracked: Byte, hideAuthor: Byte, commentsStatus: Byte): ArticlesRow = {
    articleId :: locale :: userId :: journalId :: sectionId :: language :: commentsToEd :: citations :: dateSubmitted :: lastModified :: dateStatusModified :: status :: submissionProgress :: currentRound :: submissionFileId :: revisedFileId :: reviewFileId :: editorFileId :: pages :: doi :: fastTracked :: hideAuthor :: commentsStatus :: HNil
  }
  /** GetResult implicit for fetching ArticlesRow objects using plain SQL queries */
  implicit def GetResultArticlesRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Long]], e3: GR[Option[java.sql.Timestamp]], e4: GR[Byte]): GR[ArticlesRow] = GR{
    prs => <<[Long] :: <<?[String] :: <<[Long] :: <<[Long] :: <<?[Long] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<[Byte] :: <<[Byte] :: <<[Byte] :: <<?[Long] :: <<?[Long] :: <<?[Long] :: <<?[Long] :: <<?[String] :: <<?[String] :: <<[Byte] :: <<[Byte] :: <<[Byte] :: HNil
  }
  /** Table description of table articles. Objects of this class serve as prototypes for rows in queries. */
  class Articles(tag: Tag) extends Table[ArticlesRow](tag, "articles") {
    def * = articleId :: locale :: userId :: journalId :: sectionId :: language :: commentsToEd :: citations :: dateSubmitted :: lastModified :: dateStatusModified :: status :: submissionProgress :: currentRound :: submissionFileId :: revisedFileId :: reviewFileId :: editorFileId :: pages :: doi :: fastTracked :: hideAuthor :: commentsStatus :: HNil
    
    /** Database column article_id AutoInc, PrimaryKey */
    val articleId: Column[Long] = column[Long]("article_id", O.AutoInc, O.PrimaryKey)
    /** Database column locale  */
    val locale: Column[Option[String]] = column[Option[String]]("locale")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column section_id  */
    val sectionId: Column[Option[Long]] = column[Option[Long]]("section_id")
    /** Database column language Default(None) */
    val language: Column[Option[String]] = column[Option[String]]("language", O.Default(None))
    /** Database column comments_to_ed  */
    val commentsToEd: Column[Option[String]] = column[Option[String]]("comments_to_ed")
    /** Database column citations  */
    val citations: Column[Option[String]] = column[Option[String]]("citations")
    /** Database column date_submitted  */
    val dateSubmitted: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_submitted")
    /** Database column last_modified  */
    val lastModified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_modified")
    /** Database column date_status_modified  */
    val dateStatusModified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_status_modified")
    /** Database column status  */
    val status: Column[Byte] = column[Byte]("status")
    /** Database column submission_progress  */
    val submissionProgress: Column[Byte] = column[Byte]("submission_progress")
    /** Database column current_round  */
    val currentRound: Column[Byte] = column[Byte]("current_round")
    /** Database column submission_file_id  */
    val submissionFileId: Column[Option[Long]] = column[Option[Long]]("submission_file_id")
    /** Database column revised_file_id  */
    val revisedFileId: Column[Option[Long]] = column[Option[Long]]("revised_file_id")
    /** Database column review_file_id  */
    val reviewFileId: Column[Option[Long]] = column[Option[Long]]("review_file_id")
    /** Database column editor_file_id  */
    val editorFileId: Column[Option[Long]] = column[Option[Long]]("editor_file_id")
    /** Database column pages  */
    val pages: Column[Option[String]] = column[Option[String]]("pages")
    /** Database column doi  */
    val doi: Column[Option[String]] = column[Option[String]]("doi")
    /** Database column fast_tracked  */
    val fastTracked: Column[Byte] = column[Byte]("fast_tracked")
    /** Database column hide_author  */
    val hideAuthor: Column[Byte] = column[Byte]("hide_author")
    /** Database column comments_status  */
    val commentsStatus: Column[Byte] = column[Byte]("comments_status")
    
    /** Index over (journalId) (database name articles_journal_id) */
    val index1 = index("articles_journal_id", journalId :: HNil)
    /** Index over (sectionId) (database name articles_section_id) */
    val index2 = index("articles_section_id", sectionId :: HNil)
    /** Index over (userId) (database name articles_user_id) */
    val index3 = index("articles_user_id", userId :: HNil)
  }
  /** Collection-like TableQuery object for table Articles */
  lazy val Articles = new TableQuery(tag => new Articles(tag))
  
  /** Entity class storing rows of table ArticleSearchKeywordList
   *  @param keywordId Database column keyword_id AutoInc, PrimaryKey
   *  @param keywordText Database column keyword_text  */
  case class ArticleSearchKeywordListRow(keywordId: Long, keywordText: String)
  /** GetResult implicit for fetching ArticleSearchKeywordListRow objects using plain SQL queries */
  implicit def GetResultArticleSearchKeywordListRow(implicit e0: GR[Long], e1: GR[String]): GR[ArticleSearchKeywordListRow] = GR{
    prs => ArticleSearchKeywordListRow.tupled((<<[Long], <<[String]))
  }
  /** Table description of table article_search_keyword_list. Objects of this class serve as prototypes for rows in queries. */
  class ArticleSearchKeywordList(tag: Tag) extends Table[ArticleSearchKeywordListRow](tag, "article_search_keyword_list") {
    def * = (keywordId, keywordText) <> (ArticleSearchKeywordListRow.tupled, ArticleSearchKeywordListRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (keywordId.?, keywordText.?).shaped.<>({r=> _1.map(_=> ArticleSearchKeywordListRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column keyword_id AutoInc, PrimaryKey */
    val keywordId: Column[Long] = column[Long]("keyword_id", O.AutoInc, O.PrimaryKey)
    /** Database column keyword_text  */
    val keywordText: Column[String] = column[String]("keyword_text")
    
    /** Uniqueness Index over (keywordText) (database name article_search_keyword_text) */
    val index1 = index("article_search_keyword_text", keywordText, unique=true)
  }
  /** Collection-like TableQuery object for table ArticleSearchKeywordList */
  lazy val ArticleSearchKeywordList = new TableQuery(tag => new ArticleSearchKeywordList(tag))
  
  /** Entity class storing rows of table ArticleSearchObjectKeywords
   *  @param objectId Database column object_id 
   *  @param keywordId Database column keyword_id 
   *  @param pos Database column pos  */
  case class ArticleSearchObjectKeywordsRow(objectId: Long, keywordId: Long, pos: Int)
  /** GetResult implicit for fetching ArticleSearchObjectKeywordsRow objects using plain SQL queries */
  implicit def GetResultArticleSearchObjectKeywordsRow(implicit e0: GR[Long], e1: GR[Int]): GR[ArticleSearchObjectKeywordsRow] = GR{
    prs => ArticleSearchObjectKeywordsRow.tupled((<<[Long], <<[Long], <<[Int]))
  }
  /** Table description of table article_search_object_keywords. Objects of this class serve as prototypes for rows in queries. */
  class ArticleSearchObjectKeywords(tag: Tag) extends Table[ArticleSearchObjectKeywordsRow](tag, "article_search_object_keywords") {
    def * = (objectId, keywordId, pos) <> (ArticleSearchObjectKeywordsRow.tupled, ArticleSearchObjectKeywordsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (objectId.?, keywordId.?, pos.?).shaped.<>({r=> _1.map(_=> ArticleSearchObjectKeywordsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column object_id  */
    val objectId: Column[Long] = column[Long]("object_id")
    /** Database column keyword_id  */
    val keywordId: Column[Long] = column[Long]("keyword_id")
    /** Database column pos  */
    val pos: Column[Int] = column[Int]("pos")
    
    /** Index over (keywordId) (database name article_search_object_keywords_keyword_id) */
    val index1 = index("article_search_object_keywords_keyword_id", keywordId)
    /** Uniqueness Index over (objectId,pos) (database name article_search_object_keywords_pkey) */
    val index2 = index("article_search_object_keywords_pkey", (objectId, pos), unique=true)
  }
  /** Collection-like TableQuery object for table ArticleSearchObjectKeywords */
  lazy val ArticleSearchObjectKeywords = new TableQuery(tag => new ArticleSearchObjectKeywords(tag))
  
  /** Entity class storing rows of table ArticleSearchObjects
   *  @param objectId Database column object_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param `type` Database column type 
   *  @param assocId Database column assoc_id  */
  case class ArticleSearchObjectsRow(objectId: Long, articleId: Long, `type`: Int, assocId: Option[Long])
  /** GetResult implicit for fetching ArticleSearchObjectsRow objects using plain SQL queries */
  implicit def GetResultArticleSearchObjectsRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Option[Long]]): GR[ArticleSearchObjectsRow] = GR{
    prs => ArticleSearchObjectsRow.tupled((<<[Long], <<[Long], <<[Int], <<?[Long]))
  }
  /** Table description of table article_search_objects. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class ArticleSearchObjects(tag: Tag) extends Table[ArticleSearchObjectsRow](tag, "article_search_objects") {
    def * = (objectId, articleId, `type`, assocId) <> (ArticleSearchObjectsRow.tupled, ArticleSearchObjectsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (objectId.?, articleId.?, `type`.?, assocId).shaped.<>({r=> _1.map(_=> ArticleSearchObjectsRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column object_id AutoInc, PrimaryKey */
    val objectId: Column[Long] = column[Long]("object_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column type 
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Column[Int] = column[Int]("type")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
  }
  /** Collection-like TableQuery object for table ArticleSearchObjects */
  lazy val ArticleSearchObjects = new TableQuery(tag => new ArticleSearchObjects(tag))
  
  /** Entity class storing rows of table ArticleSettings
   *  @param articleId Database column article_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class ArticleSettingsRow(articleId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching ArticleSettingsRow objects using plain SQL queries */
  implicit def GetResultArticleSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ArticleSettingsRow] = GR{
    prs => ArticleSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table article_settings. Objects of this class serve as prototypes for rows in queries. */
  class ArticleSettings(tag: Tag) extends Table[ArticleSettingsRow](tag, "article_settings") {
    def * = (articleId, locale, settingName, settingValue, settingType) <> (ArticleSettingsRow.tupled, ArticleSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (articleId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> ArticleSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (articleId) (database name article_settings_article_id) */
    val index1 = index("article_settings_article_id", articleId)
    /** Uniqueness Index over (articleId,locale,settingName) (database name article_settings_pkey) */
    val index2 = index("article_settings_pkey", (articleId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table ArticleSettings */
  lazy val ArticleSettings = new TableQuery(tag => new ArticleSettings(tag))
  
  /** Entity class storing rows of table ArticleSuppFileSettings
   *  @param suppId Database column supp_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class ArticleSuppFileSettingsRow(suppId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching ArticleSuppFileSettingsRow objects using plain SQL queries */
  implicit def GetResultArticleSuppFileSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ArticleSuppFileSettingsRow] = GR{
    prs => ArticleSuppFileSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table article_supp_file_settings. Objects of this class serve as prototypes for rows in queries. */
  class ArticleSuppFileSettings(tag: Tag) extends Table[ArticleSuppFileSettingsRow](tag, "article_supp_file_settings") {
    def * = (suppId, locale, settingName, settingValue, settingType) <> (ArticleSuppFileSettingsRow.tupled, ArticleSuppFileSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (suppId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> ArticleSuppFileSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column supp_id  */
    val suppId: Column[Long] = column[Long]("supp_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (suppId,locale,settingName) (database name article_supp_file_settings_pkey) */
    val index1 = index("article_supp_file_settings_pkey", (suppId, locale, settingName), unique=true)
    /** Index over (suppId) (database name article_supp_file_settings_supp_id) */
    val index2 = index("article_supp_file_settings_supp_id", suppId)
  }
  /** Collection-like TableQuery object for table ArticleSuppFileSettings */
  lazy val ArticleSuppFileSettings = new TableQuery(tag => new ArticleSuppFileSettings(tag))
  
  /** Entity class storing rows of table ArticleSupplementaryFiles
   *  @param suppId Database column supp_id AutoInc, PrimaryKey
   *  @param fileId Database column file_id 
   *  @param articleId Database column article_id 
   *  @param `type` Database column type 
   *  @param language Database column language 
   *  @param publicSuppFileId Database column public_supp_file_id 
   *  @param dateCreated Database column date_created 
   *  @param showReviewers Database column show_reviewers Default(None)
   *  @param dateSubmitted Database column date_submitted 
   *  @param seq Database column seq Default(0.0) */
  case class ArticleSupplementaryFilesRow(suppId: Long, fileId: Long, articleId: Long, `type`: Option[String], language: Option[String], publicSuppFileId: Option[String], dateCreated: Option[java.sql.Date], showReviewers: Option[Byte] = None, dateSubmitted: java.sql.Timestamp, seq: Double = 0.0)
  /** GetResult implicit for fetching ArticleSupplementaryFilesRow objects using plain SQL queries */
  implicit def GetResultArticleSupplementaryFilesRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[java.sql.Date]], e3: GR[Option[Byte]], e4: GR[java.sql.Timestamp], e5: GR[Double]): GR[ArticleSupplementaryFilesRow] = GR{
    prs => ArticleSupplementaryFilesRow.tupled((<<[Long], <<[Long], <<[Long], <<?[String], <<?[String], <<?[String], <<?[java.sql.Date], <<?[Byte], <<[java.sql.Timestamp], <<[Double]))
  }
  /** Table description of table article_supplementary_files. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class ArticleSupplementaryFiles(tag: Tag) extends Table[ArticleSupplementaryFilesRow](tag, "article_supplementary_files") {
    def * = (suppId, fileId, articleId, `type`, language, publicSuppFileId, dateCreated, showReviewers, dateSubmitted, seq) <> (ArticleSupplementaryFilesRow.tupled, ArticleSupplementaryFilesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (suppId.?, fileId.?, articleId.?, `type`, language, publicSuppFileId, dateCreated, showReviewers, dateSubmitted.?, seq.?).shaped.<>({r=> _1.map(_=> ArticleSupplementaryFilesRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column supp_id AutoInc, PrimaryKey */
    val suppId: Column[Long] = column[Long]("supp_id", O.AutoInc, O.PrimaryKey)
    /** Database column file_id  */
    val fileId: Column[Long] = column[Long]("file_id")
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column type 
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Column[Option[String]] = column[Option[String]]("type")
    /** Database column language  */
    val language: Column[Option[String]] = column[Option[String]]("language")
    /** Database column public_supp_file_id  */
    val publicSuppFileId: Column[Option[String]] = column[Option[String]]("public_supp_file_id")
    /** Database column date_created  */
    val dateCreated: Column[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date_created")
    /** Database column show_reviewers Default(None) */
    val showReviewers: Column[Option[Byte]] = column[Option[Byte]]("show_reviewers", O.Default(None))
    /** Database column date_submitted  */
    val dateSubmitted: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_submitted")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    
    /** Index over (articleId) (database name article_supplementary_files_article_id) */
    val index1 = index("article_supplementary_files_article_id", articleId)
    /** Index over (fileId) (database name article_supplementary_files_file_id) */
    val index2 = index("article_supplementary_files_file_id", fileId)
    /** Index over (publicSuppFileId) (database name supp_public_supp_file_id) */
    val index3 = index("supp_public_supp_file_id", publicSuppFileId)
  }
  /** Collection-like TableQuery object for table ArticleSupplementaryFiles */
  lazy val ArticleSupplementaryFiles = new TableQuery(tag => new ArticleSupplementaryFiles(tag))
  
  /** Entity class storing rows of table ArticleXmlGalleys
   *  @param xmlGalleyId Database column xml_galley_id AutoInc, PrimaryKey
   *  @param galleyId Database column galley_id 
   *  @param articleId Database column article_id 
   *  @param label Database column label 
   *  @param galleyType Database column galley_type 
   *  @param views Database column views Default(0) */
  case class ArticleXmlGalleysRow(xmlGalleyId: Long, galleyId: Long, articleId: Long, label: String, galleyType: String, views: Int = 0)
  /** GetResult implicit for fetching ArticleXmlGalleysRow objects using plain SQL queries */
  implicit def GetResultArticleXmlGalleysRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Int]): GR[ArticleXmlGalleysRow] = GR{
    prs => ArticleXmlGalleysRow.tupled((<<[Long], <<[Long], <<[Long], <<[String], <<[String], <<[Int]))
  }
  /** Table description of table article_xml_galleys. Objects of this class serve as prototypes for rows in queries. */
  class ArticleXmlGalleys(tag: Tag) extends Table[ArticleXmlGalleysRow](tag, "article_xml_galleys") {
    def * = (xmlGalleyId, galleyId, articleId, label, galleyType, views) <> (ArticleXmlGalleysRow.tupled, ArticleXmlGalleysRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (xmlGalleyId.?, galleyId.?, articleId.?, label.?, galleyType.?, views.?).shaped.<>({r=> _1.map(_=> ArticleXmlGalleysRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column xml_galley_id AutoInc, PrimaryKey */
    val xmlGalleyId: Column[Long] = column[Long]("xml_galley_id", O.AutoInc, O.PrimaryKey)
    /** Database column galley_id  */
    val galleyId: Column[Long] = column[Long]("galley_id")
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column label  */
    val label: Column[String] = column[String]("label")
    /** Database column galley_type  */
    val galleyType: Column[String] = column[String]("galley_type")
    /** Database column views Default(0) */
    val views: Column[Int] = column[Int]("views", O.Default(0))
  }
  /** Collection-like TableQuery object for table ArticleXmlGalleys */
  lazy val ArticleXmlGalleys = new TableQuery(tag => new ArticleXmlGalleys(tag))
  
  /** Entity class storing rows of table Authors
   *  @param authorId Database column author_id AutoInc, PrimaryKey
   *  @param submissionId Database column submission_id 
   *  @param primaryContact Database column primary_contact 
   *  @param seq Database column seq Default(0.0)
   *  @param firstName Database column first_name 
   *  @param middleName Database column middle_name 
   *  @param lastName Database column last_name 
   *  @param country Database column country 
   *  @param email Database column email 
   *  @param url Database column url 
   *  @param userGroupId Database column user_group_id  */
  case class AuthorsRow(authorId: Long, submissionId: Long, primaryContact: Byte, seq: Double = 0.0, firstName: String, middleName: Option[String], lastName: String, country: Option[String], email: String, url: Option[String], userGroupId: Option[Long])
  /** GetResult implicit for fetching AuthorsRow objects using plain SQL queries */
  implicit def GetResultAuthorsRow(implicit e0: GR[Long], e1: GR[Byte], e2: GR[Double], e3: GR[String], e4: GR[Option[String]], e5: GR[Option[Long]]): GR[AuthorsRow] = GR{
    prs => AuthorsRow.tupled((<<[Long], <<[Long], <<[Byte], <<[Double], <<[String], <<?[String], <<[String], <<?[String], <<[String], <<?[String], <<?[Long]))
  }
  /** Table description of table authors. Objects of this class serve as prototypes for rows in queries. */
  class Authors(tag: Tag) extends Table[AuthorsRow](tag, "authors") {
    def * = (authorId, submissionId, primaryContact, seq, firstName, middleName, lastName, country, email, url, userGroupId) <> (AuthorsRow.tupled, AuthorsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (authorId.?, submissionId.?, primaryContact.?, seq.?, firstName.?, middleName, lastName.?, country, email.?, url, userGroupId).shaped.<>({r=> _1.map(_=> AuthorsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7.get, _8, _9.get, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column author_id AutoInc, PrimaryKey */
    val authorId: Column[Long] = column[Long]("author_id", O.AutoInc, O.PrimaryKey)
    /** Database column submission_id  */
    val submissionId: Column[Long] = column[Long]("submission_id")
    /** Database column primary_contact  */
    val primaryContact: Column[Byte] = column[Byte]("primary_contact")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    /** Database column first_name  */
    val firstName: Column[String] = column[String]("first_name")
    /** Database column middle_name  */
    val middleName: Column[Option[String]] = column[Option[String]]("middle_name")
    /** Database column last_name  */
    val lastName: Column[String] = column[String]("last_name")
    /** Database column country  */
    val country: Column[Option[String]] = column[Option[String]]("country")
    /** Database column email  */
    val email: Column[String] = column[String]("email")
    /** Database column url  */
    val url: Column[Option[String]] = column[Option[String]]("url")
    /** Database column user_group_id  */
    val userGroupId: Column[Option[Long]] = column[Option[Long]]("user_group_id")
    
    /** Index over (submissionId) (database name authors_submission_id) */
    val index1 = index("authors_submission_id", submissionId)
  }
  /** Collection-like TableQuery object for table Authors */
  lazy val Authors = new TableQuery(tag => new Authors(tag))
  
  /** Entity class storing rows of table AuthorSettings
   *  @param authorId Database column author_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class AuthorSettingsRow(authorId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching AuthorSettingsRow objects using plain SQL queries */
  implicit def GetResultAuthorSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[AuthorSettingsRow] = GR{
    prs => AuthorSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table author_settings. Objects of this class serve as prototypes for rows in queries. */
  class AuthorSettings(tag: Tag) extends Table[AuthorSettingsRow](tag, "author_settings") {
    def * = (authorId, locale, settingName, settingValue, settingType) <> (AuthorSettingsRow.tupled, AuthorSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (authorId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> AuthorSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column author_id  */
    val authorId: Column[Long] = column[Long]("author_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (authorId) (database name author_settings_author_id) */
    val index1 = index("author_settings_author_id", authorId)
    /** Uniqueness Index over (authorId,locale,settingName) (database name author_settings_pkey) */
    val index2 = index("author_settings_pkey", (authorId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table AuthorSettings */
  lazy val AuthorSettings = new TableQuery(tag => new AuthorSettings(tag))
  
  /** Entity class storing rows of table AuthSources
   *  @param authId Database column auth_id AutoInc, PrimaryKey
   *  @param title Database column title 
   *  @param plugin Database column plugin 
   *  @param authDefault Database column auth_default 
   *  @param settings Database column settings  */
  case class AuthSourcesRow(authId: Long, title: String, plugin: String, authDefault: Byte, settings: Option[String])
  /** GetResult implicit for fetching AuthSourcesRow objects using plain SQL queries */
  implicit def GetResultAuthSourcesRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Byte], e3: GR[Option[String]]): GR[AuthSourcesRow] = GR{
    prs => AuthSourcesRow.tupled((<<[Long], <<[String], <<[String], <<[Byte], <<?[String]))
  }
  /** Table description of table auth_sources. Objects of this class serve as prototypes for rows in queries. */
  class AuthSources(tag: Tag) extends Table[AuthSourcesRow](tag, "auth_sources") {
    def * = (authId, title, plugin, authDefault, settings) <> (AuthSourcesRow.tupled, AuthSourcesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (authId.?, title.?, plugin.?, authDefault.?, settings).shaped.<>({r=> _1.map(_=> AuthSourcesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column auth_id AutoInc, PrimaryKey */
    val authId: Column[Long] = column[Long]("auth_id", O.AutoInc, O.PrimaryKey)
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column plugin  */
    val plugin: Column[String] = column[String]("plugin")
    /** Database column auth_default  */
    val authDefault: Column[Byte] = column[Byte]("auth_default")
    /** Database column settings  */
    val settings: Column[Option[String]] = column[Option[String]]("settings")
  }
  /** Collection-like TableQuery object for table AuthSources */
  lazy val AuthSources = new TableQuery(tag => new AuthSources(tag))
  
  /** Entity class storing rows of table BooksForReview
   *  @param bookId Database column book_id AutoInc, PrimaryKey
   *  @param journalId Database column journal_id 
   *  @param status Database column status 
   *  @param authorType Database column author_type 
   *  @param publisher Database column publisher 
   *  @param year Database column year 
   *  @param language Database column language 
   *  @param copy Database column copy 
   *  @param url Database column url 
   *  @param edition Database column edition 
   *  @param pages Database column pages 
   *  @param isbn Database column isbn 
   *  @param dateCreated Database column date_created 
   *  @param dateRequested Database column date_requested 
   *  @param dateAssigned Database column date_assigned 
   *  @param dateMailed Database column date_mailed 
   *  @param dateDue Database column date_due 
   *  @param dateSubmitted Database column date_submitted 
   *  @param userId Database column user_id 
   *  @param editorId Database column editor_id 
   *  @param articleId Database column article_id 
   *  @param notes Database column notes  */
  case class BooksForReviewRow(bookId: Long, journalId: Long, status: Short, authorType: Short, publisher: String, year: Short, language: String, copy: Byte, url: Option[String], edition: Option[Byte], pages: Option[Short], isbn: Option[String], dateCreated: java.sql.Timestamp, dateRequested: Option[java.sql.Timestamp], dateAssigned: Option[java.sql.Timestamp], dateMailed: Option[java.sql.Timestamp], dateDue: Option[java.sql.Timestamp], dateSubmitted: Option[java.sql.Timestamp], userId: Option[Long], editorId: Option[Long], articleId: Option[Long], notes: Option[String])
  /** GetResult implicit for fetching BooksForReviewRow objects using plain SQL queries */
  implicit def GetResultBooksForReviewRow(implicit e0: GR[Long], e1: GR[Short], e2: GR[String], e3: GR[Byte], e4: GR[Option[String]], e5: GR[Option[Byte]], e6: GR[Option[Short]], e7: GR[java.sql.Timestamp], e8: GR[Option[java.sql.Timestamp]], e9: GR[Option[Long]]): GR[BooksForReviewRow] = GR{
    prs => BooksForReviewRow.tupled((<<[Long], <<[Long], <<[Short], <<[Short], <<[String], <<[Short], <<[String], <<[Byte], <<?[String], <<?[Byte], <<?[Short], <<?[String], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[Long], <<?[Long], <<?[Long], <<?[String]))
  }
  /** Table description of table books_for_review. Objects of this class serve as prototypes for rows in queries. */
  class BooksForReview(tag: Tag) extends Table[BooksForReviewRow](tag, "books_for_review") {
    def * = (bookId, journalId, status, authorType, publisher, year, language, copy, url, edition, pages, isbn, dateCreated, dateRequested, dateAssigned, dateMailed, dateDue, dateSubmitted, userId, editorId, articleId, notes) <> (BooksForReviewRow.tupled, BooksForReviewRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (bookId.?, journalId.?, status.?, authorType.?, publisher.?, year.?, language.?, copy.?, url, edition, pages, isbn, dateCreated.?, dateRequested, dateAssigned, dateMailed, dateDue, dateSubmitted, userId, editorId, articleId, notes).shaped.<>({r=> _1.map(_=> BooksForReviewRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9, _10, _11, _12, _13.get, _14, _15, _16, _17, _18, _19, _20, _21, _22)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column book_id AutoInc, PrimaryKey */
    val bookId: Column[Long] = column[Long]("book_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column status  */
    val status: Column[Short] = column[Short]("status")
    /** Database column author_type  */
    val authorType: Column[Short] = column[Short]("author_type")
    /** Database column publisher  */
    val publisher: Column[String] = column[String]("publisher")
    /** Database column year  */
    val year: Column[Short] = column[Short]("year")
    /** Database column language  */
    val language: Column[String] = column[String]("language")
    /** Database column copy  */
    val copy: Column[Byte] = column[Byte]("copy")
    /** Database column url  */
    val url: Column[Option[String]] = column[Option[String]]("url")
    /** Database column edition  */
    val edition: Column[Option[Byte]] = column[Option[Byte]]("edition")
    /** Database column pages  */
    val pages: Column[Option[Short]] = column[Option[Short]]("pages")
    /** Database column isbn  */
    val isbn: Column[Option[String]] = column[Option[String]]("isbn")
    /** Database column date_created  */
    val dateCreated: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_created")
    /** Database column date_requested  */
    val dateRequested: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_requested")
    /** Database column date_assigned  */
    val dateAssigned: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_assigned")
    /** Database column date_mailed  */
    val dateMailed: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_mailed")
    /** Database column date_due  */
    val dateDue: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_due")
    /** Database column date_submitted  */
    val dateSubmitted: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_submitted")
    /** Database column user_id  */
    val userId: Column[Option[Long]] = column[Option[Long]]("user_id")
    /** Database column editor_id  */
    val editorId: Column[Option[Long]] = column[Option[Long]]("editor_id")
    /** Database column article_id  */
    val articleId: Column[Option[Long]] = column[Option[Long]]("article_id")
    /** Database column notes  */
    val notes: Column[Option[String]] = column[Option[String]]("notes")
    
    /** Index over (bookId) (database name bfr_id) */
    val index1 = index("bfr_id", bookId)
  }
  /** Collection-like TableQuery object for table BooksForReview */
  lazy val BooksForReview = new TableQuery(tag => new BooksForReview(tag))
  
  /** Entity class storing rows of table BooksForReviewAuthors
   *  @param authorId Database column author_id AutoInc, PrimaryKey
   *  @param bookId Database column book_id 
   *  @param seq Database column seq Default(0.0)
   *  @param firstName Database column first_name 
   *  @param middleName Database column middle_name 
   *  @param lastName Database column last_name  */
  case class BooksForReviewAuthorsRow(authorId: Long, bookId: Long, seq: Double = 0.0, firstName: String, middleName: Option[String], lastName: String)
  /** GetResult implicit for fetching BooksForReviewAuthorsRow objects using plain SQL queries */
  implicit def GetResultBooksForReviewAuthorsRow(implicit e0: GR[Long], e1: GR[Double], e2: GR[String], e3: GR[Option[String]]): GR[BooksForReviewAuthorsRow] = GR{
    prs => BooksForReviewAuthorsRow.tupled((<<[Long], <<[Long], <<[Double], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table books_for_review_authors. Objects of this class serve as prototypes for rows in queries. */
  class BooksForReviewAuthors(tag: Tag) extends Table[BooksForReviewAuthorsRow](tag, "books_for_review_authors") {
    def * = (authorId, bookId, seq, firstName, middleName, lastName) <> (BooksForReviewAuthorsRow.tupled, BooksForReviewAuthorsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (authorId.?, bookId.?, seq.?, firstName.?, middleName, lastName.?).shaped.<>({r=> _1.map(_=> BooksForReviewAuthorsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column author_id AutoInc, PrimaryKey */
    val authorId: Column[Long] = column[Long]("author_id", O.AutoInc, O.PrimaryKey)
    /** Database column book_id  */
    val bookId: Column[Long] = column[Long]("book_id")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    /** Database column first_name  */
    val firstName: Column[String] = column[String]("first_name")
    /** Database column middle_name  */
    val middleName: Column[Option[String]] = column[Option[String]]("middle_name")
    /** Database column last_name  */
    val lastName: Column[String] = column[String]("last_name")
    
    /** Index over (bookId) (database name bfr_book_id) */
    val index1 = index("bfr_book_id", bookId)
  }
  /** Collection-like TableQuery object for table BooksForReviewAuthors */
  lazy val BooksForReviewAuthors = new TableQuery(tag => new BooksForReviewAuthors(tag))
  
  /** Entity class storing rows of table BooksForReviewSettings
   *  @param bookId Database column book_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class BooksForReviewSettingsRow(bookId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching BooksForReviewSettingsRow objects using plain SQL queries */
  implicit def GetResultBooksForReviewSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[BooksForReviewSettingsRow] = GR{
    prs => BooksForReviewSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table books_for_review_settings. Objects of this class serve as prototypes for rows in queries. */
  class BooksForReviewSettings(tag: Tag) extends Table[BooksForReviewSettingsRow](tag, "books_for_review_settings") {
    def * = (bookId, locale, settingName, settingValue, settingType) <> (BooksForReviewSettingsRow.tupled, BooksForReviewSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (bookId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> BooksForReviewSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column book_id  */
    val bookId: Column[Long] = column[Long]("book_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (bookId) (database name bfr_settings_book_id) */
    val index1 = index("bfr_settings_book_id", bookId)
    /** Uniqueness Index over (bookId,locale,settingName) (database name bfr_settings_pkey) */
    val index2 = index("bfr_settings_pkey", (bookId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table BooksForReviewSettings */
  lazy val BooksForReviewSettings = new TableQuery(tag => new BooksForReviewSettings(tag))
  
  /** Entity class storing rows of table Captchas
   *  @param captchaId Database column captcha_id AutoInc, PrimaryKey
   *  @param sessionId Database column session_id 
   *  @param value Database column value 
   *  @param dateCreated Database column date_created  */
  case class CaptchasRow(captchaId: Long, sessionId: String, value: String, dateCreated: java.sql.Timestamp)
  /** GetResult implicit for fetching CaptchasRow objects using plain SQL queries */
  implicit def GetResultCaptchasRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[CaptchasRow] = GR{
    prs => CaptchasRow.tupled((<<[Long], <<[String], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table captchas. Objects of this class serve as prototypes for rows in queries. */
  class Captchas(tag: Tag) extends Table[CaptchasRow](tag, "captchas") {
    def * = (captchaId, sessionId, value, dateCreated) <> (CaptchasRow.tupled, CaptchasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (captchaId.?, sessionId.?, value.?, dateCreated.?).shaped.<>({r=> _1.map(_=> CaptchasRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column captcha_id AutoInc, PrimaryKey */
    val captchaId: Column[Long] = column[Long]("captcha_id", O.AutoInc, O.PrimaryKey)
    /** Database column session_id  */
    val sessionId: Column[String] = column[String]("session_id")
    /** Database column value  */
    val value: Column[String] = column[String]("value")
    /** Database column date_created  */
    val dateCreated: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_created")
  }
  /** Collection-like TableQuery object for table Captchas */
  lazy val Captchas = new TableQuery(tag => new Captchas(tag))
  
  /** Entity class storing rows of table Citations
   *  @param citationId Database column citation_id AutoInc, PrimaryKey
   *  @param assocType Database column assoc_type Default(0)
   *  @param assocId Database column assoc_id Default(0)
   *  @param citationState Database column citation_state 
   *  @param rawCitation Database column raw_citation 
   *  @param seq Database column seq Default(0)
   *  @param lockId Database column lock_id  */
  case class CitationsRow(citationId: Long, assocType: Long = 0L, assocId: Long = 0L, citationState: Long, rawCitation: Option[String], seq: Long = 0L, lockId: Option[String])
  /** GetResult implicit for fetching CitationsRow objects using plain SQL queries */
  implicit def GetResultCitationsRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[CitationsRow] = GR{
    prs => CitationsRow.tupled((<<[Long], <<[Long], <<[Long], <<[Long], <<?[String], <<[Long], <<?[String]))
  }
  /** Table description of table citations. Objects of this class serve as prototypes for rows in queries. */
  class Citations(tag: Tag) extends Table[CitationsRow](tag, "citations") {
    def * = (citationId, assocType, assocId, citationState, rawCitation, seq, lockId) <> (CitationsRow.tupled, CitationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (citationId.?, assocType.?, assocId.?, citationState.?, rawCitation, seq.?, lockId).shaped.<>({r=> _1.map(_=> CitationsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column citation_id AutoInc, PrimaryKey */
    val citationId: Column[Long] = column[Long]("citation_id", O.AutoInc, O.PrimaryKey)
    /** Database column assoc_type Default(0) */
    val assocType: Column[Long] = column[Long]("assoc_type", O.Default(0L))
    /** Database column assoc_id Default(0) */
    val assocId: Column[Long] = column[Long]("assoc_id", O.Default(0L))
    /** Database column citation_state  */
    val citationState: Column[Long] = column[Long]("citation_state")
    /** Database column raw_citation  */
    val rawCitation: Column[Option[String]] = column[Option[String]]("raw_citation")
    /** Database column seq Default(0) */
    val seq: Column[Long] = column[Long]("seq", O.Default(0L))
    /** Database column lock_id  */
    val lockId: Column[Option[String]] = column[Option[String]]("lock_id")
    
    /** Index over (assocType,assocId) (database name citations_assoc) */
    val index1 = index("citations_assoc", (assocType, assocId))
    /** Uniqueness Index over (assocType,assocId,seq) (database name citations_assoc_seq) */
    val index2 = index("citations_assoc_seq", (assocType, assocId, seq), unique=true)
  }
  /** Collection-like TableQuery object for table Citations */
  lazy val Citations = new TableQuery(tag => new Citations(tag))
  
  /** Entity class storing rows of table CitationSettings
   *  @param citationId Database column citation_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class CitationSettingsRow(citationId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching CitationSettingsRow objects using plain SQL queries */
  implicit def GetResultCitationSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[CitationSettingsRow] = GR{
    prs => CitationSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table citation_settings. Objects of this class serve as prototypes for rows in queries. */
  class CitationSettings(tag: Tag) extends Table[CitationSettingsRow](tag, "citation_settings") {
    def * = (citationId, locale, settingName, settingValue, settingType) <> (CitationSettingsRow.tupled, CitationSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (citationId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> CitationSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column citation_id  */
    val citationId: Column[Long] = column[Long]("citation_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (citationId) (database name citation_settings_citation_id) */
    val index1 = index("citation_settings_citation_id", citationId)
    /** Uniqueness Index over (citationId,locale,settingName) (database name citation_settings_pkey) */
    val index2 = index("citation_settings_pkey", (citationId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table CitationSettings */
  lazy val CitationSettings = new TableQuery(tag => new CitationSettings(tag))
  
  /** Entity class storing rows of table Comments
   *  @param commentId Database column comment_id AutoInc, PrimaryKey
   *  @param submissionId Database column submission_id 
   *  @param parentCommentId Database column parent_comment_id 
   *  @param numChildren Database column num_children Default(0)
   *  @param userId Database column user_id 
   *  @param posterIp Database column poster_ip 
   *  @param posterName Database column poster_name 
   *  @param posterEmail Database column poster_email 
   *  @param title Database column title 
   *  @param body Database column body 
   *  @param datePosted Database column date_posted 
   *  @param dateModified Database column date_modified  */
  case class CommentsRow(commentId: Long, submissionId: Long, parentCommentId: Option[Long], numChildren: Int = 0, userId: Option[Long], posterIp: String, posterName: Option[String], posterEmail: Option[String], title: String, body: Option[String], datePosted: Option[java.sql.Timestamp], dateModified: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching CommentsRow objects using plain SQL queries */
  implicit def GetResultCommentsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Int], e3: GR[String], e4: GR[Option[String]], e5: GR[Option[java.sql.Timestamp]]): GR[CommentsRow] = GR{
    prs => CommentsRow.tupled((<<[Long], <<[Long], <<?[Long], <<[Int], <<?[Long], <<[String], <<?[String], <<?[String], <<[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table comments. Objects of this class serve as prototypes for rows in queries. */
  class Comments(tag: Tag) extends Table[CommentsRow](tag, "comments") {
    def * = (commentId, submissionId, parentCommentId, numChildren, userId, posterIp, posterName, posterEmail, title, body, datePosted, dateModified) <> (CommentsRow.tupled, CommentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (commentId.?, submissionId.?, parentCommentId, numChildren.?, userId, posterIp.?, posterName, posterEmail, title.?, body, datePosted, dateModified).shaped.<>({r=> _1.map(_=> CommentsRow.tupled((_1.get, _2.get, _3, _4.get, _5, _6.get, _7, _8, _9.get, _10, _11, _12)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column comment_id AutoInc, PrimaryKey */
    val commentId: Column[Long] = column[Long]("comment_id", O.AutoInc, O.PrimaryKey)
    /** Database column submission_id  */
    val submissionId: Column[Long] = column[Long]("submission_id")
    /** Database column parent_comment_id  */
    val parentCommentId: Column[Option[Long]] = column[Option[Long]]("parent_comment_id")
    /** Database column num_children Default(0) */
    val numChildren: Column[Int] = column[Int]("num_children", O.Default(0))
    /** Database column user_id  */
    val userId: Column[Option[Long]] = column[Option[Long]]("user_id")
    /** Database column poster_ip  */
    val posterIp: Column[String] = column[String]("poster_ip")
    /** Database column poster_name  */
    val posterName: Column[Option[String]] = column[Option[String]]("poster_name")
    /** Database column poster_email  */
    val posterEmail: Column[Option[String]] = column[Option[String]]("poster_email")
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column body  */
    val body: Column[Option[String]] = column[Option[String]]("body")
    /** Database column date_posted  */
    val datePosted: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_posted")
    /** Database column date_modified  */
    val dateModified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_modified")
    
    /** Index over (parentCommentId) (database name comments_parent_comment_id) */
    val index1 = index("comments_parent_comment_id", parentCommentId)
    /** Index over (submissionId) (database name comments_submission_id) */
    val index2 = index("comments_submission_id", submissionId)
    /** Index over (userId) (database name comments_user_id) */
    val index3 = index("comments_user_id", userId)
  }
  /** Collection-like TableQuery object for table Comments */
  lazy val Comments = new TableQuery(tag => new Comments(tag))
  
  /** Entity class storing rows of table CompletedPayments
   *  @param completedPaymentId Database column completed_payment_id AutoInc, PrimaryKey
   *  @param timestamp Database column timestamp 
   *  @param paymentType Database column payment_type 
   *  @param journalId Database column journal_id 
   *  @param userId Database column user_id 
   *  @param assocId Database column assoc_id 
   *  @param amount Database column amount 
   *  @param currencyCodeAlpha Database column currency_code_alpha 
   *  @param paymentMethodPluginName Database column payment_method_plugin_name  */
  case class CompletedPaymentsRow(completedPaymentId: Long, timestamp: java.sql.Timestamp, paymentType: Long, journalId: Long, userId: Option[Long], assocId: Option[Long], amount: Double, currencyCodeAlpha: Option[String], paymentMethodPluginName: Option[String])
  /** GetResult implicit for fetching CompletedPaymentsRow objects using plain SQL queries */
  implicit def GetResultCompletedPaymentsRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp], e2: GR[Option[Long]], e3: GR[Double], e4: GR[Option[String]]): GR[CompletedPaymentsRow] = GR{
    prs => CompletedPaymentsRow.tupled((<<[Long], <<[java.sql.Timestamp], <<[Long], <<[Long], <<?[Long], <<?[Long], <<[Double], <<?[String], <<?[String]))
  }
  /** Table description of table completed_payments. Objects of this class serve as prototypes for rows in queries. */
  class CompletedPayments(tag: Tag) extends Table[CompletedPaymentsRow](tag, "completed_payments") {
    def * = (completedPaymentId, timestamp, paymentType, journalId, userId, assocId, amount, currencyCodeAlpha, paymentMethodPluginName) <> (CompletedPaymentsRow.tupled, CompletedPaymentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (completedPaymentId.?, timestamp.?, paymentType.?, journalId.?, userId, assocId, amount.?, currencyCodeAlpha, paymentMethodPluginName).shaped.<>({r=> _1.map(_=> CompletedPaymentsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7.get, _8, _9)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column completed_payment_id AutoInc, PrimaryKey */
    val completedPaymentId: Column[Long] = column[Long]("completed_payment_id", O.AutoInc, O.PrimaryKey)
    /** Database column timestamp  */
    val timestamp: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("timestamp")
    /** Database column payment_type  */
    val paymentType: Column[Long] = column[Long]("payment_type")
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column user_id  */
    val userId: Column[Option[Long]] = column[Option[Long]]("user_id")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    /** Database column amount  */
    val amount: Column[Double] = column[Double]("amount")
    /** Database column currency_code_alpha  */
    val currencyCodeAlpha: Column[Option[String]] = column[Option[String]]("currency_code_alpha")
    /** Database column payment_method_plugin_name  */
    val paymentMethodPluginName: Column[Option[String]] = column[Option[String]]("payment_method_plugin_name")
  }
  /** Collection-like TableQuery object for table CompletedPayments */
  lazy val CompletedPayments = new TableQuery(tag => new CompletedPayments(tag))
  
  /** Entity class storing rows of table ControlledVocabEntries
   *  @param controlledVocabEntryId Database column controlled_vocab_entry_id AutoInc, PrimaryKey
   *  @param controlledVocabId Database column controlled_vocab_id 
   *  @param seq Database column seq  */
  case class ControlledVocabEntriesRow(controlledVocabEntryId: Long, controlledVocabId: Long, seq: Option[Double])
  /** GetResult implicit for fetching ControlledVocabEntriesRow objects using plain SQL queries */
  implicit def GetResultControlledVocabEntriesRow(implicit e0: GR[Long], e1: GR[Option[Double]]): GR[ControlledVocabEntriesRow] = GR{
    prs => ControlledVocabEntriesRow.tupled((<<[Long], <<[Long], <<?[Double]))
  }
  /** Table description of table controlled_vocab_entries. Objects of this class serve as prototypes for rows in queries. */
  class ControlledVocabEntries(tag: Tag) extends Table[ControlledVocabEntriesRow](tag, "controlled_vocab_entries") {
    def * = (controlledVocabEntryId, controlledVocabId, seq) <> (ControlledVocabEntriesRow.tupled, ControlledVocabEntriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (controlledVocabEntryId.?, controlledVocabId.?, seq).shaped.<>({r=> _1.map(_=> ControlledVocabEntriesRow.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column controlled_vocab_entry_id AutoInc, PrimaryKey */
    val controlledVocabEntryId: Column[Long] = column[Long]("controlled_vocab_entry_id", O.AutoInc, O.PrimaryKey)
    /** Database column controlled_vocab_id  */
    val controlledVocabId: Column[Long] = column[Long]("controlled_vocab_id")
    /** Database column seq  */
    val seq: Column[Option[Double]] = column[Option[Double]]("seq")
    
    /** Index over (controlledVocabId,seq) (database name controlled_vocab_entries_cv_id) */
    val index1 = index("controlled_vocab_entries_cv_id", (controlledVocabId, seq))
  }
  /** Collection-like TableQuery object for table ControlledVocabEntries */
  lazy val ControlledVocabEntries = new TableQuery(tag => new ControlledVocabEntries(tag))
  
  /** Entity class storing rows of table ControlledVocabEntrySettings
   *  @param controlledVocabEntryId Database column controlled_vocab_entry_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class ControlledVocabEntrySettingsRow(controlledVocabEntryId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching ControlledVocabEntrySettingsRow objects using plain SQL queries */
  implicit def GetResultControlledVocabEntrySettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ControlledVocabEntrySettingsRow] = GR{
    prs => ControlledVocabEntrySettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table controlled_vocab_entry_settings. Objects of this class serve as prototypes for rows in queries. */
  class ControlledVocabEntrySettings(tag: Tag) extends Table[ControlledVocabEntrySettingsRow](tag, "controlled_vocab_entry_settings") {
    def * = (controlledVocabEntryId, locale, settingName, settingValue, settingType) <> (ControlledVocabEntrySettingsRow.tupled, ControlledVocabEntrySettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (controlledVocabEntryId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> ControlledVocabEntrySettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column controlled_vocab_entry_id  */
    val controlledVocabEntryId: Column[Long] = column[Long]("controlled_vocab_entry_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (controlledVocabEntryId) (database name c_v_e_s_entry_id) */
    val index1 = index("c_v_e_s_entry_id", controlledVocabEntryId)
    /** Uniqueness Index over (controlledVocabEntryId,locale,settingName) (database name c_v_e_s_pkey) */
    val index2 = index("c_v_e_s_pkey", (controlledVocabEntryId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table ControlledVocabEntrySettings */
  lazy val ControlledVocabEntrySettings = new TableQuery(tag => new ControlledVocabEntrySettings(tag))
  
  /** Entity class storing rows of table ControlledVocabs
   *  @param controlledVocabId Database column controlled_vocab_id AutoInc, PrimaryKey
   *  @param symbolic Database column symbolic 
   *  @param assocType Database column assoc_type Default(0)
   *  @param assocId Database column assoc_id Default(0) */
  case class ControlledVocabsRow(controlledVocabId: Long, symbolic: String, assocType: Long = 0L, assocId: Long = 0L)
  /** GetResult implicit for fetching ControlledVocabsRow objects using plain SQL queries */
  implicit def GetResultControlledVocabsRow(implicit e0: GR[Long], e1: GR[String]): GR[ControlledVocabsRow] = GR{
    prs => ControlledVocabsRow.tupled((<<[Long], <<[String], <<[Long], <<[Long]))
  }
  /** Table description of table controlled_vocabs. Objects of this class serve as prototypes for rows in queries. */
  class ControlledVocabs(tag: Tag) extends Table[ControlledVocabsRow](tag, "controlled_vocabs") {
    def * = (controlledVocabId, symbolic, assocType, assocId) <> (ControlledVocabsRow.tupled, ControlledVocabsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (controlledVocabId.?, symbolic.?, assocType.?, assocId.?).shaped.<>({r=> _1.map(_=> ControlledVocabsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column controlled_vocab_id AutoInc, PrimaryKey */
    val controlledVocabId: Column[Long] = column[Long]("controlled_vocab_id", O.AutoInc, O.PrimaryKey)
    /** Database column symbolic  */
    val symbolic: Column[String] = column[String]("symbolic")
    /** Database column assoc_type Default(0) */
    val assocType: Column[Long] = column[Long]("assoc_type", O.Default(0L))
    /** Database column assoc_id Default(0) */
    val assocId: Column[Long] = column[Long]("assoc_id", O.Default(0L))
    
    /** Uniqueness Index over (symbolic,assocType,assocId) (database name controlled_vocab_symbolic) */
    val index1 = index("controlled_vocab_symbolic", (symbolic, assocType, assocId), unique=true)
  }
  /** Collection-like TableQuery object for table ControlledVocabs */
  lazy val ControlledVocabs = new TableQuery(tag => new ControlledVocabs(tag))
  
  /** Entity class storing rows of table CounterMonthlyLog
   *  @param year Database column year 
   *  @param month Database column month 
   *  @param journalId Database column journal_id 
   *  @param countHtml Database column count_html Default(0)
   *  @param countPdf Database column count_pdf Default(0)
   *  @param countOther Database column count_other Default(0) */
  case class CounterMonthlyLogRow(year: Long, month: Long, journalId: Long, countHtml: Long = 0L, countPdf: Long = 0L, countOther: Long = 0L)
  /** GetResult implicit for fetching CounterMonthlyLogRow objects using plain SQL queries */
  implicit def GetResultCounterMonthlyLogRow(implicit e0: GR[Long]): GR[CounterMonthlyLogRow] = GR{
    prs => CounterMonthlyLogRow.tupled((<<[Long], <<[Long], <<[Long], <<[Long], <<[Long], <<[Long]))
  }
  /** Table description of table counter_monthly_log. Objects of this class serve as prototypes for rows in queries. */
  class CounterMonthlyLog(tag: Tag) extends Table[CounterMonthlyLogRow](tag, "counter_monthly_log") {
    def * = (year, month, journalId, countHtml, countPdf, countOther) <> (CounterMonthlyLogRow.tupled, CounterMonthlyLogRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (year.?, month.?, journalId.?, countHtml.?, countPdf.?, countOther.?).shaped.<>({r=> _1.map(_=> CounterMonthlyLogRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column year  */
    val year: Column[Long] = column[Long]("year")
    /** Database column month  */
    val month: Column[Long] = column[Long]("month")
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column count_html Default(0) */
    val countHtml: Column[Long] = column[Long]("count_html", O.Default(0L))
    /** Database column count_pdf Default(0) */
    val countPdf: Column[Long] = column[Long]("count_pdf", O.Default(0L))
    /** Database column count_other Default(0) */
    val countOther: Column[Long] = column[Long]("count_other", O.Default(0L))
    
    /** Uniqueness Index over (year,month,journalId) (database name counter_monthly_log_key) */
    val index1 = index("counter_monthly_log_key", (year, month, journalId), unique=true)
  }
  /** Collection-like TableQuery object for table CounterMonthlyLog */
  lazy val CounterMonthlyLog = new TableQuery(tag => new CounterMonthlyLog(tag))
  
  /** Entity class storing rows of table CustomIssueOrders
   *  @param issueId Database column issue_id 
   *  @param journalId Database column journal_id 
   *  @param seq Database column seq Default(0.0) */
  case class CustomIssueOrdersRow(issueId: Long, journalId: Long, seq: Double = 0.0)
  /** GetResult implicit for fetching CustomIssueOrdersRow objects using plain SQL queries */
  implicit def GetResultCustomIssueOrdersRow(implicit e0: GR[Long], e1: GR[Double]): GR[CustomIssueOrdersRow] = GR{
    prs => CustomIssueOrdersRow.tupled((<<[Long], <<[Long], <<[Double]))
  }
  /** Table description of table custom_issue_orders. Objects of this class serve as prototypes for rows in queries. */
  class CustomIssueOrders(tag: Tag) extends Table[CustomIssueOrdersRow](tag, "custom_issue_orders") {
    def * = (issueId, journalId, seq) <> (CustomIssueOrdersRow.tupled, CustomIssueOrdersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (issueId.?, journalId.?, seq.?).shaped.<>({r=> _1.map(_=> CustomIssueOrdersRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column issue_id  */
    val issueId: Column[Long] = column[Long]("issue_id")
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    
    /** Uniqueness Index over (issueId) (database name custom_issue_orders_pkey) */
    val index1 = index("custom_issue_orders_pkey", issueId, unique=true)
  }
  /** Collection-like TableQuery object for table CustomIssueOrders */
  lazy val CustomIssueOrders = new TableQuery(tag => new CustomIssueOrders(tag))
  
  /** Entity class storing rows of table CustomSectionOrders
   *  @param issueId Database column issue_id 
   *  @param sectionId Database column section_id 
   *  @param seq Database column seq Default(0.0) */
  case class CustomSectionOrdersRow(issueId: Long, sectionId: Long, seq: Double = 0.0)
  /** GetResult implicit for fetching CustomSectionOrdersRow objects using plain SQL queries */
  implicit def GetResultCustomSectionOrdersRow(implicit e0: GR[Long], e1: GR[Double]): GR[CustomSectionOrdersRow] = GR{
    prs => CustomSectionOrdersRow.tupled((<<[Long], <<[Long], <<[Double]))
  }
  /** Table description of table custom_section_orders. Objects of this class serve as prototypes for rows in queries. */
  class CustomSectionOrders(tag: Tag) extends Table[CustomSectionOrdersRow](tag, "custom_section_orders") {
    def * = (issueId, sectionId, seq) <> (CustomSectionOrdersRow.tupled, CustomSectionOrdersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (issueId.?, sectionId.?, seq.?).shaped.<>({r=> _1.map(_=> CustomSectionOrdersRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column issue_id  */
    val issueId: Column[Long] = column[Long]("issue_id")
    /** Database column section_id  */
    val sectionId: Column[Long] = column[Long]("section_id")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    
    /** Uniqueness Index over (issueId,sectionId) (database name custom_section_orders_pkey) */
    val index1 = index("custom_section_orders_pkey", (issueId, sectionId), unique=true)
  }
  /** Collection-like TableQuery object for table CustomSectionOrders */
  lazy val CustomSectionOrders = new TableQuery(tag => new CustomSectionOrders(tag))
  
  /** Entity class storing rows of table EditAssignments
   *  @param editId Database column edit_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param editorId Database column editor_id 
   *  @param canEdit Database column can_edit 
   *  @param canReview Database column can_review 
   *  @param dateAssigned Database column date_assigned 
   *  @param dateNotified Database column date_notified 
   *  @param dateUnderway Database column date_underway  */
  case class EditAssignmentsRow(editId: Long, articleId: Long, editorId: Long, canEdit: Byte, canReview: Byte, dateAssigned: Option[java.sql.Timestamp], dateNotified: Option[java.sql.Timestamp], dateUnderway: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching EditAssignmentsRow objects using plain SQL queries */
  implicit def GetResultEditAssignmentsRow(implicit e0: GR[Long], e1: GR[Byte], e2: GR[Option[java.sql.Timestamp]]): GR[EditAssignmentsRow] = GR{
    prs => EditAssignmentsRow.tupled((<<[Long], <<[Long], <<[Long], <<[Byte], <<[Byte], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table edit_assignments. Objects of this class serve as prototypes for rows in queries. */
  class EditAssignments(tag: Tag) extends Table[EditAssignmentsRow](tag, "edit_assignments") {
    def * = (editId, articleId, editorId, canEdit, canReview, dateAssigned, dateNotified, dateUnderway) <> (EditAssignmentsRow.tupled, EditAssignmentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (editId.?, articleId.?, editorId.?, canEdit.?, canReview.?, dateAssigned, dateNotified, dateUnderway).shaped.<>({r=> _1.map(_=> EditAssignmentsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column edit_id AutoInc, PrimaryKey */
    val editId: Column[Long] = column[Long]("edit_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column editor_id  */
    val editorId: Column[Long] = column[Long]("editor_id")
    /** Database column can_edit  */
    val canEdit: Column[Byte] = column[Byte]("can_edit")
    /** Database column can_review  */
    val canReview: Column[Byte] = column[Byte]("can_review")
    /** Database column date_assigned  */
    val dateAssigned: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_assigned")
    /** Database column date_notified  */
    val dateNotified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_notified")
    /** Database column date_underway  */
    val dateUnderway: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_underway")
    
    /** Index over (articleId) (database name edit_assignments_article_id) */
    val index1 = index("edit_assignments_article_id", articleId)
    /** Index over (editorId) (database name edit_assignments_editor_id) */
    val index2 = index("edit_assignments_editor_id", editorId)
  }
  /** Collection-like TableQuery object for table EditAssignments */
  lazy val EditAssignments = new TableQuery(tag => new EditAssignments(tag))
  
  /** Entity class storing rows of table EditDecisions
   *  @param editDecisionId Database column edit_decision_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param round Database column round 
   *  @param editorId Database column editor_id 
   *  @param decision Database column decision 
   *  @param dateDecided Database column date_decided  */
  case class EditDecisionsRow(editDecisionId: Long, articleId: Long, round: Byte, editorId: Long, decision: Byte, dateDecided: java.sql.Timestamp)
  /** GetResult implicit for fetching EditDecisionsRow objects using plain SQL queries */
  implicit def GetResultEditDecisionsRow(implicit e0: GR[Long], e1: GR[Byte], e2: GR[java.sql.Timestamp]): GR[EditDecisionsRow] = GR{
    prs => EditDecisionsRow.tupled((<<[Long], <<[Long], <<[Byte], <<[Long], <<[Byte], <<[java.sql.Timestamp]))
  }
  /** Table description of table edit_decisions. Objects of this class serve as prototypes for rows in queries. */
  class EditDecisions(tag: Tag) extends Table[EditDecisionsRow](tag, "edit_decisions") {
    def * = (editDecisionId, articleId, round, editorId, decision, dateDecided) <> (EditDecisionsRow.tupled, EditDecisionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (editDecisionId.?, articleId.?, round.?, editorId.?, decision.?, dateDecided.?).shaped.<>({r=> _1.map(_=> EditDecisionsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column edit_decision_id AutoInc, PrimaryKey */
    val editDecisionId: Column[Long] = column[Long]("edit_decision_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column round  */
    val round: Column[Byte] = column[Byte]("round")
    /** Database column editor_id  */
    val editorId: Column[Long] = column[Long]("editor_id")
    /** Database column decision  */
    val decision: Column[Byte] = column[Byte]("decision")
    /** Database column date_decided  */
    val dateDecided: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_decided")
    
    /** Index over (articleId) (database name edit_decisions_article_id) */
    val index1 = index("edit_decisions_article_id", articleId)
    /** Index over (editorId) (database name edit_decisions_editor_id) */
    val index2 = index("edit_decisions_editor_id", editorId)
  }
  /** Collection-like TableQuery object for table EditDecisions */
  lazy val EditDecisions = new TableQuery(tag => new EditDecisions(tag))
  
  /** Entity class storing rows of table EmailTemplates
   *  @param emailId Database column email_id AutoInc, PrimaryKey
   *  @param emailKey Database column email_key 
   *  @param assocType Database column assoc_type Default(Some(0))
   *  @param assocId Database column assoc_id Default(Some(0))
   *  @param enabled Database column enabled  */
  case class EmailTemplatesRow(emailId: Long, emailKey: String, assocType: Option[Long] = Some(0L), assocId: Option[Long] = Some(0L), enabled: Byte)
  /** GetResult implicit for fetching EmailTemplatesRow objects using plain SQL queries */
  implicit def GetResultEmailTemplatesRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]], e3: GR[Byte]): GR[EmailTemplatesRow] = GR{
    prs => EmailTemplatesRow.tupled((<<[Long], <<[String], <<?[Long], <<?[Long], <<[Byte]))
  }
  /** Table description of table email_templates. Objects of this class serve as prototypes for rows in queries. */
  class EmailTemplates(tag: Tag) extends Table[EmailTemplatesRow](tag, "email_templates") {
    def * = (emailId, emailKey, assocType, assocId, enabled) <> (EmailTemplatesRow.tupled, EmailTemplatesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (emailId.?, emailKey.?, assocType, assocId, enabled.?).shaped.<>({r=> _1.map(_=> EmailTemplatesRow.tupled((_1.get, _2.get, _3, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column email_id AutoInc, PrimaryKey */
    val emailId: Column[Long] = column[Long]("email_id", O.AutoInc, O.PrimaryKey)
    /** Database column email_key  */
    val emailKey: Column[String] = column[String]("email_key")
    /** Database column assoc_type Default(Some(0)) */
    val assocType: Column[Option[Long]] = column[Option[Long]]("assoc_type", O.Default(Some(0L)))
    /** Database column assoc_id Default(Some(0)) */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id", O.Default(Some(0L)))
    /** Database column enabled  */
    val enabled: Column[Byte] = column[Byte]("enabled")
    
    /** Index over (assocType,assocId) (database name email_templates_assoc) */
    val index1 = index("email_templates_assoc", (assocType, assocId))
    /** Uniqueness Index over (emailKey,assocType,assocId) (database name email_templates_email_key) */
    val index2 = index("email_templates_email_key", (emailKey, assocType, assocId), unique=true)
  }
  /** Collection-like TableQuery object for table EmailTemplates */
  lazy val EmailTemplates = new TableQuery(tag => new EmailTemplates(tag))
  
  /** Entity class storing rows of table EmailTemplatesData
   *  @param emailKey Database column email_key 
   *  @param locale Database column locale 
   *  @param assocType Database column assoc_type Default(Some(0))
   *  @param assocId Database column assoc_id Default(Some(0))
   *  @param subject Database column subject 
   *  @param body Database column body  */
  case class EmailTemplatesDataRow(emailKey: String, locale: String, assocType: Option[Long] = Some(0L), assocId: Option[Long] = Some(0L), subject: String, body: Option[String])
  /** GetResult implicit for fetching EmailTemplatesDataRow objects using plain SQL queries */
  implicit def GetResultEmailTemplatesDataRow(implicit e0: GR[String], e1: GR[Option[Long]], e2: GR[Option[String]]): GR[EmailTemplatesDataRow] = GR{
    prs => EmailTemplatesDataRow.tupled((<<[String], <<[String], <<?[Long], <<?[Long], <<[String], <<?[String]))
  }
  /** Table description of table email_templates_data. Objects of this class serve as prototypes for rows in queries. */
  class EmailTemplatesData(tag: Tag) extends Table[EmailTemplatesDataRow](tag, "email_templates_data") {
    def * = (emailKey, locale, assocType, assocId, subject, body) <> (EmailTemplatesDataRow.tupled, EmailTemplatesDataRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (emailKey.?, locale.?, assocType, assocId, subject.?, body).shaped.<>({r=> _1.map(_=> EmailTemplatesDataRow.tupled((_1.get, _2.get, _3, _4, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column email_key  */
    val emailKey: Column[String] = column[String]("email_key")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column assoc_type Default(Some(0)) */
    val assocType: Column[Option[Long]] = column[Option[Long]]("assoc_type", O.Default(Some(0L)))
    /** Database column assoc_id Default(Some(0)) */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id", O.Default(Some(0L)))
    /** Database column subject  */
    val subject: Column[String] = column[String]("subject")
    /** Database column body  */
    val body: Column[Option[String]] = column[Option[String]]("body")
    
    /** Uniqueness Index over (emailKey,locale,assocType,assocId) (database name email_templates_data_pkey) */
    val index1 = index("email_templates_data_pkey", (emailKey, locale, assocType, assocId), unique=true)
  }
  /** Collection-like TableQuery object for table EmailTemplatesData */
  lazy val EmailTemplatesData = new TableQuery(tag => new EmailTemplatesData(tag))
  
  /** Entity class storing rows of table EmailTemplatesDefault
   *  @param emailId Database column email_id AutoInc, PrimaryKey
   *  @param emailKey Database column email_key 
   *  @param canDisable Database column can_disable 
   *  @param canEdit Database column can_edit 
   *  @param fromRoleId Database column from_role_id 
   *  @param toRoleId Database column to_role_id  */
  case class EmailTemplatesDefaultRow(emailId: Long, emailKey: String, canDisable: Byte, canEdit: Byte, fromRoleId: Option[Long], toRoleId: Option[Long])
  /** GetResult implicit for fetching EmailTemplatesDefaultRow objects using plain SQL queries */
  implicit def GetResultEmailTemplatesDefaultRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Byte], e3: GR[Option[Long]]): GR[EmailTemplatesDefaultRow] = GR{
    prs => EmailTemplatesDefaultRow.tupled((<<[Long], <<[String], <<[Byte], <<[Byte], <<?[Long], <<?[Long]))
  }
  /** Table description of table email_templates_default. Objects of this class serve as prototypes for rows in queries. */
  class EmailTemplatesDefault(tag: Tag) extends Table[EmailTemplatesDefaultRow](tag, "email_templates_default") {
    def * = (emailId, emailKey, canDisable, canEdit, fromRoleId, toRoleId) <> (EmailTemplatesDefaultRow.tupled, EmailTemplatesDefaultRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (emailId.?, emailKey.?, canDisable.?, canEdit.?, fromRoleId, toRoleId).shaped.<>({r=> _1.map(_=> EmailTemplatesDefaultRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column email_id AutoInc, PrimaryKey */
    val emailId: Column[Long] = column[Long]("email_id", O.AutoInc, O.PrimaryKey)
    /** Database column email_key  */
    val emailKey: Column[String] = column[String]("email_key")
    /** Database column can_disable  */
    val canDisable: Column[Byte] = column[Byte]("can_disable")
    /** Database column can_edit  */
    val canEdit: Column[Byte] = column[Byte]("can_edit")
    /** Database column from_role_id  */
    val fromRoleId: Column[Option[Long]] = column[Option[Long]]("from_role_id")
    /** Database column to_role_id  */
    val toRoleId: Column[Option[Long]] = column[Option[Long]]("to_role_id")
    
    /** Index over (emailKey) (database name email_templates_default_email_key) */
    val index1 = index("email_templates_default_email_key", emailKey)
  }
  /** Collection-like TableQuery object for table EmailTemplatesDefault */
  lazy val EmailTemplatesDefault = new TableQuery(tag => new EmailTemplatesDefault(tag))
  
  /** Entity class storing rows of table EmailTemplatesDefaultData
   *  @param emailKey Database column email_key 
   *  @param locale Database column locale 
   *  @param subject Database column subject 
   *  @param body Database column body 
   *  @param description Database column description  */
  case class EmailTemplatesDefaultDataRow(emailKey: String, locale: String, subject: String, body: Option[String], description: Option[String])
  /** GetResult implicit for fetching EmailTemplatesDefaultDataRow objects using plain SQL queries */
  implicit def GetResultEmailTemplatesDefaultDataRow(implicit e0: GR[String], e1: GR[Option[String]]): GR[EmailTemplatesDefaultDataRow] = GR{
    prs => EmailTemplatesDefaultDataRow.tupled((<<[String], <<[String], <<[String], <<?[String], <<?[String]))
  }
  /** Table description of table email_templates_default_data. Objects of this class serve as prototypes for rows in queries. */
  class EmailTemplatesDefaultData(tag: Tag) extends Table[EmailTemplatesDefaultDataRow](tag, "email_templates_default_data") {
    def * = (emailKey, locale, subject, body, description) <> (EmailTemplatesDefaultDataRow.tupled, EmailTemplatesDefaultDataRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (emailKey.?, locale.?, subject.?, body, description).shaped.<>({r=> _1.map(_=> EmailTemplatesDefaultDataRow.tupled((_1.get, _2.get, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column email_key  */
    val emailKey: Column[String] = column[String]("email_key")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column subject  */
    val subject: Column[String] = column[String]("subject")
    /** Database column body  */
    val body: Column[Option[String]] = column[Option[String]]("body")
    /** Database column description  */
    val description: Column[Option[String]] = column[Option[String]]("description")
    
    /** Uniqueness Index over (emailKey,locale) (database name email_templates_default_data_pkey) */
    val index1 = index("email_templates_default_data_pkey", (emailKey, locale), unique=true)
  }
  /** Collection-like TableQuery object for table EmailTemplatesDefaultData */
  lazy val EmailTemplatesDefaultData = new TableQuery(tag => new EmailTemplatesDefaultData(tag))
  
  /** Entity class storing rows of table ExternalFeeds
   *  @param feedId Database column feed_id AutoInc, PrimaryKey
   *  @param journalId Database column journal_id 
   *  @param url Database column url 
   *  @param seq Database column seq Default(0.0)
   *  @param displayHomepage Database column display_homepage 
   *  @param displayBlock Database column display_block Default(0)
   *  @param limitItems Database column limit_items Default(None)
   *  @param recentItems Database column recent_items  */
  case class ExternalFeedsRow(feedId: Long, journalId: Long, url: String, seq: Double = 0.0, displayHomepage: Byte, displayBlock: Short = 0, limitItems: Option[Byte] = None, recentItems: Option[Short])
  /** GetResult implicit for fetching ExternalFeedsRow objects using plain SQL queries */
  implicit def GetResultExternalFeedsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Double], e3: GR[Byte], e4: GR[Short], e5: GR[Option[Byte]], e6: GR[Option[Short]]): GR[ExternalFeedsRow] = GR{
    prs => ExternalFeedsRow.tupled((<<[Long], <<[Long], <<[String], <<[Double], <<[Byte], <<[Short], <<?[Byte], <<?[Short]))
  }
  /** Table description of table external_feeds. Objects of this class serve as prototypes for rows in queries. */
  class ExternalFeeds(tag: Tag) extends Table[ExternalFeedsRow](tag, "external_feeds") {
    def * = (feedId, journalId, url, seq, displayHomepage, displayBlock, limitItems, recentItems) <> (ExternalFeedsRow.tupled, ExternalFeedsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (feedId.?, journalId.?, url.?, seq.?, displayHomepage.?, displayBlock.?, limitItems, recentItems).shaped.<>({r=> _1.map(_=> ExternalFeedsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column feed_id AutoInc, PrimaryKey */
    val feedId: Column[Long] = column[Long]("feed_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column url  */
    val url: Column[String] = column[String]("url")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    /** Database column display_homepage  */
    val displayHomepage: Column[Byte] = column[Byte]("display_homepage")
    /** Database column display_block Default(0) */
    val displayBlock: Column[Short] = column[Short]("display_block", O.Default(0))
    /** Database column limit_items Default(None) */
    val limitItems: Column[Option[Byte]] = column[Option[Byte]]("limit_items", O.Default(None))
    /** Database column recent_items  */
    val recentItems: Column[Option[Short]] = column[Option[Short]]("recent_items")
    
    /** Index over (journalId) (database name external_feeds_journal_id) */
    val index1 = index("external_feeds_journal_id", journalId)
  }
  /** Collection-like TableQuery object for table ExternalFeeds */
  lazy val ExternalFeeds = new TableQuery(tag => new ExternalFeeds(tag))
  
  /** Entity class storing rows of table ExternalFeedSettings
   *  @param feedId Database column feed_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class ExternalFeedSettingsRow(feedId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching ExternalFeedSettingsRow objects using plain SQL queries */
  implicit def GetResultExternalFeedSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ExternalFeedSettingsRow] = GR{
    prs => ExternalFeedSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table external_feed_settings. Objects of this class serve as prototypes for rows in queries. */
  class ExternalFeedSettings(tag: Tag) extends Table[ExternalFeedSettingsRow](tag, "external_feed_settings") {
    def * = (feedId, locale, settingName, settingValue, settingType) <> (ExternalFeedSettingsRow.tupled, ExternalFeedSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (feedId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> ExternalFeedSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column feed_id  */
    val feedId: Column[Long] = column[Long]("feed_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (feedId,locale,settingName) (database name external_feed_settings_pkey) */
    val index1 = index("external_feed_settings_pkey", (feedId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table ExternalFeedSettings */
  lazy val ExternalFeedSettings = new TableQuery(tag => new ExternalFeedSettings(tag))
  
  /** Entity class storing rows of table Filters
   *  @param filterId Database column filter_id AutoInc, PrimaryKey
   *  @param contextId Database column context_id Default(0)
   *  @param displayName Database column display_name 
   *  @param className Database column class_name 
   *  @param inputType Database column input_type 
   *  @param outputType Database column output_type 
   *  @param isTemplate Database column is_template 
   *  @param parentFilterId Database column parent_filter_id Default(0)
   *  @param seq Database column seq Default(0) */
  case class FiltersRow(filterId: Long, contextId: Long = 0L, displayName: Option[String], className: Option[String], inputType: Option[String], outputType: Option[String], isTemplate: Byte, parentFilterId: Long = 0L, seq: Long = 0L)
  /** GetResult implicit for fetching FiltersRow objects using plain SQL queries */
  implicit def GetResultFiltersRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Byte]): GR[FiltersRow] = GR{
    prs => FiltersRow.tupled((<<[Long], <<[Long], <<?[String], <<?[String], <<?[String], <<?[String], <<[Byte], <<[Long], <<[Long]))
  }
  /** Table description of table filters. Objects of this class serve as prototypes for rows in queries. */
  class Filters(tag: Tag) extends Table[FiltersRow](tag, "filters") {
    def * = (filterId, contextId, displayName, className, inputType, outputType, isTemplate, parentFilterId, seq) <> (FiltersRow.tupled, FiltersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (filterId.?, contextId.?, displayName, className, inputType, outputType, isTemplate.?, parentFilterId.?, seq.?).shaped.<>({r=> _1.map(_=> FiltersRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column filter_id AutoInc, PrimaryKey */
    val filterId: Column[Long] = column[Long]("filter_id", O.AutoInc, O.PrimaryKey)
    /** Database column context_id Default(0) */
    val contextId: Column[Long] = column[Long]("context_id", O.Default(0L))
    /** Database column display_name  */
    val displayName: Column[Option[String]] = column[Option[String]]("display_name")
    /** Database column class_name  */
    val className: Column[Option[String]] = column[Option[String]]("class_name")
    /** Database column input_type  */
    val inputType: Column[Option[String]] = column[Option[String]]("input_type")
    /** Database column output_type  */
    val outputType: Column[Option[String]] = column[Option[String]]("output_type")
    /** Database column is_template  */
    val isTemplate: Column[Byte] = column[Byte]("is_template")
    /** Database column parent_filter_id Default(0) */
    val parentFilterId: Column[Long] = column[Long]("parent_filter_id", O.Default(0L))
    /** Database column seq Default(0) */
    val seq: Column[Long] = column[Long]("seq", O.Default(0L))
  }
  /** Collection-like TableQuery object for table Filters */
  lazy val Filters = new TableQuery(tag => new Filters(tag))
  
  /** Entity class storing rows of table FilterSettings
   *  @param filterId Database column filter_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class FilterSettingsRow(filterId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching FilterSettingsRow objects using plain SQL queries */
  implicit def GetResultFilterSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[FilterSettingsRow] = GR{
    prs => FilterSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table filter_settings. Objects of this class serve as prototypes for rows in queries. */
  class FilterSettings(tag: Tag) extends Table[FilterSettingsRow](tag, "filter_settings") {
    def * = (filterId, locale, settingName, settingValue, settingType) <> (FilterSettingsRow.tupled, FilterSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (filterId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> FilterSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column filter_id  */
    val filterId: Column[Long] = column[Long]("filter_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (filterId) (database name filter_settings_id) */
    val index1 = index("filter_settings_id", filterId)
    /** Uniqueness Index over (filterId,locale,settingName) (database name filter_settings_pkey) */
    val index2 = index("filter_settings_pkey", (filterId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table FilterSettings */
  lazy val FilterSettings = new TableQuery(tag => new FilterSettings(tag))
  
  /** Entity class storing rows of table GroupMemberships
   *  @param userId Database column user_id 
   *  @param groupId Database column group_id 
   *  @param aboutDisplayed Database column about_displayed 
   *  @param seq Database column seq Default(0.0) */
  case class GroupMembershipsRow(userId: Long, groupId: Long, aboutDisplayed: Byte, seq: Double = 0.0)
  /** GetResult implicit for fetching GroupMembershipsRow objects using plain SQL queries */
  implicit def GetResultGroupMembershipsRow(implicit e0: GR[Long], e1: GR[Byte], e2: GR[Double]): GR[GroupMembershipsRow] = GR{
    prs => GroupMembershipsRow.tupled((<<[Long], <<[Long], <<[Byte], <<[Double]))
  }
  /** Table description of table group_memberships. Objects of this class serve as prototypes for rows in queries. */
  class GroupMemberships(tag: Tag) extends Table[GroupMembershipsRow](tag, "group_memberships") {
    def * = (userId, groupId, aboutDisplayed, seq) <> (GroupMembershipsRow.tupled, GroupMembershipsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (userId.?, groupId.?, aboutDisplayed.?, seq.?).shaped.<>({r=> _1.map(_=> GroupMembershipsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column group_id  */
    val groupId: Column[Long] = column[Long]("group_id")
    /** Database column about_displayed  */
    val aboutDisplayed: Column[Byte] = column[Byte]("about_displayed")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    
    /** Uniqueness Index over (userId,groupId) (database name group_memberships_pkey) */
    val index1 = index("group_memberships_pkey", (userId, groupId), unique=true)
  }
  /** Collection-like TableQuery object for table GroupMemberships */
  lazy val GroupMemberships = new TableQuery(tag => new GroupMemberships(tag))
  
  /** Entity class storing rows of table Groups
   *  @param groupId Database column group_id AutoInc, PrimaryKey
   *  @param assocType Database column assoc_type 
   *  @param publishEmail Database column publish_email 
   *  @param assocId Database column assoc_id 
   *  @param context Database column context 
   *  @param aboutDisplayed Database column about_displayed 
   *  @param seq Database column seq Default(0.0) */
  case class GroupsRow(groupId: Long, assocType: Option[Short], publishEmail: Option[Short], assocId: Option[Long], context: Option[Long], aboutDisplayed: Byte, seq: Double = 0.0)
  /** GetResult implicit for fetching GroupsRow objects using plain SQL queries */
  implicit def GetResultGroupsRow(implicit e0: GR[Long], e1: GR[Option[Short]], e2: GR[Option[Long]], e3: GR[Byte], e4: GR[Double]): GR[GroupsRow] = GR{
    prs => GroupsRow.tupled((<<[Long], <<?[Short], <<?[Short], <<?[Long], <<?[Long], <<[Byte], <<[Double]))
  }
  /** Table description of table groups. Objects of this class serve as prototypes for rows in queries. */
  class Groups(tag: Tag) extends Table[GroupsRow](tag, "groups") {
    def * = (groupId, assocType, publishEmail, assocId, context, aboutDisplayed, seq) <> (GroupsRow.tupled, GroupsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (groupId.?, assocType, publishEmail, assocId, context, aboutDisplayed.?, seq.?).shaped.<>({r=> _1.map(_=> GroupsRow.tupled((_1.get, _2, _3, _4, _5, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column group_id AutoInc, PrimaryKey */
    val groupId: Column[Long] = column[Long]("group_id", O.AutoInc, O.PrimaryKey)
    /** Database column assoc_type  */
    val assocType: Column[Option[Short]] = column[Option[Short]]("assoc_type")
    /** Database column publish_email  */
    val publishEmail: Column[Option[Short]] = column[Option[Short]]("publish_email")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    /** Database column context  */
    val context: Column[Option[Long]] = column[Option[Long]]("context")
    /** Database column about_displayed  */
    val aboutDisplayed: Column[Byte] = column[Byte]("about_displayed")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    
    /** Index over (assocType,assocId) (database name groups_assoc) */
    val index1 = index("groups_assoc", (assocType, assocId))
  }
  /** Collection-like TableQuery object for table Groups */
  lazy val Groups = new TableQuery(tag => new Groups(tag))
  
  /** Entity class storing rows of table GroupSettings
   *  @param groupId Database column group_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class GroupSettingsRow(groupId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching GroupSettingsRow objects using plain SQL queries */
  implicit def GetResultGroupSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[GroupSettingsRow] = GR{
    prs => GroupSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table group_settings. Objects of this class serve as prototypes for rows in queries. */
  class GroupSettings(tag: Tag) extends Table[GroupSettingsRow](tag, "group_settings") {
    def * = (groupId, locale, settingName, settingValue, settingType) <> (GroupSettingsRow.tupled, GroupSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (groupId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> GroupSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column group_id  */
    val groupId: Column[Long] = column[Long]("group_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (groupId) (database name group_settings_group_id) */
    val index1 = index("group_settings_group_id", groupId)
    /** Uniqueness Index over (groupId,locale,settingName) (database name group_settings_pkey) */
    val index2 = index("group_settings_pkey", (groupId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table GroupSettings */
  lazy val GroupSettings = new TableQuery(tag => new GroupSettings(tag))
  
  /** Entity class storing rows of table InstitutionalSubscriptionIp
   *  @param institutionalSubscriptionIpId Database column institutional_subscription_ip_id AutoInc, PrimaryKey
   *  @param subscriptionId Database column subscription_id 
   *  @param ipString Database column ip_string 
   *  @param ipStart Database column ip_start 
   *  @param ipEnd Database column ip_end  */
  case class InstitutionalSubscriptionIpRow(institutionalSubscriptionIpId: Long, subscriptionId: Long, ipString: String, ipStart: Long, ipEnd: Option[Long])
  /** GetResult implicit for fetching InstitutionalSubscriptionIpRow objects using plain SQL queries */
  implicit def GetResultInstitutionalSubscriptionIpRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]]): GR[InstitutionalSubscriptionIpRow] = GR{
    prs => InstitutionalSubscriptionIpRow.tupled((<<[Long], <<[Long], <<[String], <<[Long], <<?[Long]))
  }
  /** Table description of table institutional_subscription_ip. Objects of this class serve as prototypes for rows in queries. */
  class InstitutionalSubscriptionIp(tag: Tag) extends Table[InstitutionalSubscriptionIpRow](tag, "institutional_subscription_ip") {
    def * = (institutionalSubscriptionIpId, subscriptionId, ipString, ipStart, ipEnd) <> (InstitutionalSubscriptionIpRow.tupled, InstitutionalSubscriptionIpRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (institutionalSubscriptionIpId.?, subscriptionId.?, ipString.?, ipStart.?, ipEnd).shaped.<>({r=> _1.map(_=> InstitutionalSubscriptionIpRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column institutional_subscription_ip_id AutoInc, PrimaryKey */
    val institutionalSubscriptionIpId: Column[Long] = column[Long]("institutional_subscription_ip_id", O.AutoInc, O.PrimaryKey)
    /** Database column subscription_id  */
    val subscriptionId: Column[Long] = column[Long]("subscription_id")
    /** Database column ip_string  */
    val ipString: Column[String] = column[String]("ip_string")
    /** Database column ip_start  */
    val ipStart: Column[Long] = column[Long]("ip_start")
    /** Database column ip_end  */
    val ipEnd: Column[Option[Long]] = column[Option[Long]]("ip_end")
    
    /** Index over (ipEnd) (database name institutional_subscription_ip_end) */
    val index1 = index("institutional_subscription_ip_end", ipEnd)
    /** Index over (ipStart) (database name institutional_subscription_ip_start) */
    val index2 = index("institutional_subscription_ip_start", ipStart)
    /** Index over (subscriptionId) (database name institutional_subscription_ip_subscription_id) */
    val index3 = index("institutional_subscription_ip_subscription_id", subscriptionId)
  }
  /** Collection-like TableQuery object for table InstitutionalSubscriptionIp */
  lazy val InstitutionalSubscriptionIp = new TableQuery(tag => new InstitutionalSubscriptionIp(tag))
  
  /** Entity class storing rows of table InstitutionalSubscriptions
   *  @param institutionalSubscriptionId Database column institutional_subscription_id AutoInc, PrimaryKey
   *  @param subscriptionId Database column subscription_id 
   *  @param institutionName Database column institution_name 
   *  @param mailingAddress Database column mailing_address 
   *  @param domain Database column domain  */
  case class InstitutionalSubscriptionsRow(institutionalSubscriptionId: Long, subscriptionId: Long, institutionName: String, mailingAddress: Option[String], domain: Option[String])
  /** GetResult implicit for fetching InstitutionalSubscriptionsRow objects using plain SQL queries */
  implicit def GetResultInstitutionalSubscriptionsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[InstitutionalSubscriptionsRow] = GR{
    prs => InstitutionalSubscriptionsRow.tupled((<<[Long], <<[Long], <<[String], <<?[String], <<?[String]))
  }
  /** Table description of table institutional_subscriptions. Objects of this class serve as prototypes for rows in queries. */
  class InstitutionalSubscriptions(tag: Tag) extends Table[InstitutionalSubscriptionsRow](tag, "institutional_subscriptions") {
    def * = (institutionalSubscriptionId, subscriptionId, institutionName, mailingAddress, domain) <> (InstitutionalSubscriptionsRow.tupled, InstitutionalSubscriptionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (institutionalSubscriptionId.?, subscriptionId.?, institutionName.?, mailingAddress, domain).shaped.<>({r=> _1.map(_=> InstitutionalSubscriptionsRow.tupled((_1.get, _2.get, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column institutional_subscription_id AutoInc, PrimaryKey */
    val institutionalSubscriptionId: Column[Long] = column[Long]("institutional_subscription_id", O.AutoInc, O.PrimaryKey)
    /** Database column subscription_id  */
    val subscriptionId: Column[Long] = column[Long]("subscription_id")
    /** Database column institution_name  */
    val institutionName: Column[String] = column[String]("institution_name")
    /** Database column mailing_address  */
    val mailingAddress: Column[Option[String]] = column[Option[String]]("mailing_address")
    /** Database column domain  */
    val domain: Column[Option[String]] = column[Option[String]]("domain")
    
    /** Index over (domain) (database name institutional_subscriptions_domain) */
    val index1 = index("institutional_subscriptions_domain", domain)
    /** Index over (subscriptionId) (database name institutional_subscriptions_subscription_id) */
    val index2 = index("institutional_subscriptions_subscription_id", subscriptionId)
  }
  /** Collection-like TableQuery object for table InstitutionalSubscriptions */
  lazy val InstitutionalSubscriptions = new TableQuery(tag => new InstitutionalSubscriptions(tag))
  
  /** Entity class storing rows of table Issues
   *  @param issueId Database column issue_id AutoInc, PrimaryKey
   *  @param journalId Database column journal_id 
   *  @param volume Database column volume 
   *  @param number Database column number 
   *  @param year Database column year 
   *  @param published Database column published 
   *  @param current Database column current 
   *  @param datePublished Database column date_published 
   *  @param dateNotified Database column date_notified 
   *  @param accessStatus Database column access_status 
   *  @param openAccessDate Database column open_access_date 
   *  @param publicIssueId Database column public_issue_id 
   *  @param showVolume Database column show_volume 
   *  @param showNumber Database column show_number 
   *  @param showYear Database column show_year 
   *  @param showTitle Database column show_title 
   *  @param styleFileName Database column style_file_name 
   *  @param originalStyleFileName Database column original_style_file_name  */
  case class IssuesRow(issueId: Long, journalId: Long, volume: Option[Short], number: Option[String], year: Option[Short], published: Byte, current: Byte, datePublished: Option[java.sql.Timestamp], dateNotified: Option[java.sql.Timestamp], accessStatus: Byte, openAccessDate: Option[java.sql.Timestamp], publicIssueId: Option[String], showVolume: Byte, showNumber: Byte, showYear: Byte, showTitle: Byte, styleFileName: Option[String], originalStyleFileName: Option[String])
  /** GetResult implicit for fetching IssuesRow objects using plain SQL queries */
  implicit def GetResultIssuesRow(implicit e0: GR[Long], e1: GR[Option[Short]], e2: GR[Option[String]], e3: GR[Byte], e4: GR[Option[java.sql.Timestamp]]): GR[IssuesRow] = GR{
    prs => IssuesRow.tupled((<<[Long], <<[Long], <<?[Short], <<?[String], <<?[Short], <<[Byte], <<[Byte], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<[Byte], <<?[java.sql.Timestamp], <<?[String], <<[Byte], <<[Byte], <<[Byte], <<[Byte], <<?[String], <<?[String]))
  }
  /** Table description of table issues. Objects of this class serve as prototypes for rows in queries. */
  class Issues(tag: Tag) extends Table[IssuesRow](tag, "issues") {
    def * = (issueId, journalId, volume, number, year, published, current, datePublished, dateNotified, accessStatus, openAccessDate, publicIssueId, showVolume, showNumber, showYear, showTitle, styleFileName, originalStyleFileName) <> (IssuesRow.tupled, IssuesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (issueId.?, journalId.?, volume, number, year, published.?, current.?, datePublished, dateNotified, accessStatus.?, openAccessDate, publicIssueId, showVolume.?, showNumber.?, showYear.?, showTitle.?, styleFileName, originalStyleFileName).shaped.<>({r=> _1.map(_=> IssuesRow.tupled((_1.get, _2.get, _3, _4, _5, _6.get, _7.get, _8, _9, _10.get, _11, _12, _13.get, _14.get, _15.get, _16.get, _17, _18)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column issue_id AutoInc, PrimaryKey */
    val issueId: Column[Long] = column[Long]("issue_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column volume  */
    val volume: Column[Option[Short]] = column[Option[Short]]("volume")
    /** Database column number  */
    val number: Column[Option[String]] = column[Option[String]]("number")
    /** Database column year  */
    val year: Column[Option[Short]] = column[Option[Short]]("year")
    /** Database column published  */
    val published: Column[Byte] = column[Byte]("published")
    /** Database column current  */
    val current: Column[Byte] = column[Byte]("current")
    /** Database column date_published  */
    val datePublished: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_published")
    /** Database column date_notified  */
    val dateNotified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_notified")
    /** Database column access_status  */
    val accessStatus: Column[Byte] = column[Byte]("access_status")
    /** Database column open_access_date  */
    val openAccessDate: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("open_access_date")
    /** Database column public_issue_id  */
    val publicIssueId: Column[Option[String]] = column[Option[String]]("public_issue_id")
    /** Database column show_volume  */
    val showVolume: Column[Byte] = column[Byte]("show_volume")
    /** Database column show_number  */
    val showNumber: Column[Byte] = column[Byte]("show_number")
    /** Database column show_year  */
    val showYear: Column[Byte] = column[Byte]("show_year")
    /** Database column show_title  */
    val showTitle: Column[Byte] = column[Byte]("show_title")
    /** Database column style_file_name  */
    val styleFileName: Column[Option[String]] = column[Option[String]]("style_file_name")
    /** Database column original_style_file_name  */
    val originalStyleFileName: Column[Option[String]] = column[Option[String]]("original_style_file_name")
    
    /** Index over (journalId) (database name issues_journal_id) */
    val index1 = index("issues_journal_id", journalId)
    /** Uniqueness Index over (publicIssueId,journalId) (database name issues_public_issue_id) */
    val index2 = index("issues_public_issue_id", (publicIssueId, journalId), unique=true)
  }
  /** Collection-like TableQuery object for table Issues */
  lazy val Issues = new TableQuery(tag => new Issues(tag))
  
  /** Entity class storing rows of table IssueSettings
   *  @param issueId Database column issue_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class IssueSettingsRow(issueId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching IssueSettingsRow objects using plain SQL queries */
  implicit def GetResultIssueSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[IssueSettingsRow] = GR{
    prs => IssueSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table issue_settings. Objects of this class serve as prototypes for rows in queries. */
  class IssueSettings(tag: Tag) extends Table[IssueSettingsRow](tag, "issue_settings") {
    def * = (issueId, locale, settingName, settingValue, settingType) <> (IssueSettingsRow.tupled, IssueSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (issueId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> IssueSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column issue_id  */
    val issueId: Column[Long] = column[Long]("issue_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (issueId) (database name issue_settings_issue_id) */
    val index1 = index("issue_settings_issue_id", issueId)
    /** Uniqueness Index over (issueId,locale,settingName) (database name issue_settings_pkey) */
    val index2 = index("issue_settings_pkey", (issueId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table IssueSettings */
  lazy val IssueSettings = new TableQuery(tag => new IssueSettings(tag))
  
  /** Entity class storing rows of table Journals
   *  @param journalId Database column journal_id AutoInc, PrimaryKey
   *  @param path Database column path 
   *  @param seq Database column seq Default(0.0)
   *  @param primaryLocale Database column primary_locale 
   *  @param enabled Database column enabled  */
  case class JournalsRow(journalId: Long, path: String, seq: Double = 0.0, primaryLocale: String, enabled: Byte)
  /** GetResult implicit for fetching JournalsRow objects using plain SQL queries */
  implicit def GetResultJournalsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Double], e3: GR[Byte]): GR[JournalsRow] = GR{
    prs => JournalsRow.tupled((<<[Long], <<[String], <<[Double], <<[String], <<[Byte]))
  }
  /** Table description of table journals. Objects of this class serve as prototypes for rows in queries. */
  class Journals(tag: Tag) extends Table[JournalsRow](tag, "journals") {
    def * = (journalId, path, seq, primaryLocale, enabled) <> (JournalsRow.tupled, JournalsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (journalId.?, path.?, seq.?, primaryLocale.?, enabled.?).shaped.<>({r=> _1.map(_=> JournalsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column journal_id AutoInc, PrimaryKey */
    val journalId: Column[Long] = column[Long]("journal_id", O.AutoInc, O.PrimaryKey)
    /** Database column path  */
    val path: Column[String] = column[String]("path")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    /** Database column primary_locale  */
    val primaryLocale: Column[String] = column[String]("primary_locale")
    /** Database column enabled  */
    val enabled: Column[Byte] = column[Byte]("enabled")
    
    /** Uniqueness Index over (path) (database name journals_path) */
    val index1 = index("journals_path", path, unique=true)
  }
  /** Collection-like TableQuery object for table Journals */
  lazy val Journals = new TableQuery(tag => new Journals(tag))
  
  /** Entity class storing rows of table JournalSettings
   *  @param journalId Database column journal_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class JournalSettingsRow(journalId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching JournalSettingsRow objects using plain SQL queries */
  implicit def GetResultJournalSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[JournalSettingsRow] = GR{
    prs => JournalSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table journal_settings. Objects of this class serve as prototypes for rows in queries. */
  class JournalSettings(tag: Tag) extends Table[JournalSettingsRow](tag, "journal_settings") {
    def * = (journalId, locale, settingName, settingValue, settingType) <> (JournalSettingsRow.tupled, JournalSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (journalId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> JournalSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (journalId) (database name journal_settings_journal_id) */
    val index1 = index("journal_settings_journal_id", journalId)
    /** Uniqueness Index over (journalId,locale,settingName) (database name journal_settings_pkey) */
    val index2 = index("journal_settings_pkey", (journalId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table JournalSettings */
  lazy val JournalSettings = new TableQuery(tag => new JournalSettings(tag))
  
  /** Entity class storing rows of table MetadataDescriptions
   *  @param metadataDescriptionId Database column metadata_description_id AutoInc, PrimaryKey
   *  @param assocType Database column assoc_type Default(0)
   *  @param assocId Database column assoc_id Default(0)
   *  @param schemaNamespace Database column schema_namespace 
   *  @param schemaNameX Database column schema_name 
   *  @param displayName Database column display_name 
   *  @param seq Database column seq Default(0) */
  case class MetadataDescriptionsRow(metadataDescriptionId: Long, assocType: Long = 0L, assocId: Long = 0L, schemaNamespace: String, schemaNameX: String, displayName: Option[String], seq: Long = 0L)
  /** GetResult implicit for fetching MetadataDescriptionsRow objects using plain SQL queries */
  implicit def GetResultMetadataDescriptionsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[MetadataDescriptionsRow] = GR{
    prs => MetadataDescriptionsRow.tupled((<<[Long], <<[Long], <<[Long], <<[String], <<[String], <<?[String], <<[Long]))
  }
  /** Table description of table metadata_descriptions. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala method names and were disambiguated: schemaName */
  class MetadataDescriptions(tag: Tag) extends Table[MetadataDescriptionsRow](tag, "metadata_descriptions") {
    def * = (metadataDescriptionId, assocType, assocId, schemaNamespace, schemaNameX, displayName, seq) <> (MetadataDescriptionsRow.tupled, MetadataDescriptionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (metadataDescriptionId.?, assocType.?, assocId.?, schemaNamespace.?, schemaNameX.?, displayName, seq.?).shaped.<>({r=> _1.map(_=> MetadataDescriptionsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column metadata_description_id AutoInc, PrimaryKey */
    val metadataDescriptionId: Column[Long] = column[Long]("metadata_description_id", O.AutoInc, O.PrimaryKey)
    /** Database column assoc_type Default(0) */
    val assocType: Column[Long] = column[Long]("assoc_type", O.Default(0L))
    /** Database column assoc_id Default(0) */
    val assocId: Column[Long] = column[Long]("assoc_id", O.Default(0L))
    /** Database column schema_namespace  */
    val schemaNamespace: Column[String] = column[String]("schema_namespace")
    /** Database column schema_name 
     *  NOTE: The name was disambiguated because it collided with Slick's method Table#schemaName. */
    val schemaNameX: Column[String] = column[String]("schema_name")
    /** Database column display_name  */
    val displayName: Column[Option[String]] = column[Option[String]]("display_name")
    /** Database column seq Default(0) */
    val seq: Column[Long] = column[Long]("seq", O.Default(0L))
    
    /** Index over (assocType,assocId) (database name metadata_descriptions_assoc) */
    val index1 = index("metadata_descriptions_assoc", (assocType, assocId))
  }
  /** Collection-like TableQuery object for table MetadataDescriptions */
  lazy val MetadataDescriptions = new TableQuery(tag => new MetadataDescriptions(tag))
  
  /** Entity class storing rows of table MetadataDescriptionSettings
   *  @param metadataDescriptionId Database column metadata_description_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class MetadataDescriptionSettingsRow(metadataDescriptionId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching MetadataDescriptionSettingsRow objects using plain SQL queries */
  implicit def GetResultMetadataDescriptionSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[MetadataDescriptionSettingsRow] = GR{
    prs => MetadataDescriptionSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table metadata_description_settings. Objects of this class serve as prototypes for rows in queries. */
  class MetadataDescriptionSettings(tag: Tag) extends Table[MetadataDescriptionSettingsRow](tag, "metadata_description_settings") {
    def * = (metadataDescriptionId, locale, settingName, settingValue, settingType) <> (MetadataDescriptionSettingsRow.tupled, MetadataDescriptionSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (metadataDescriptionId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> MetadataDescriptionSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column metadata_description_id  */
    val metadataDescriptionId: Column[Long] = column[Long]("metadata_description_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Index over (metadataDescriptionId) (database name metadata_description_settings_id) */
    val index1 = index("metadata_description_settings_id", metadataDescriptionId)
    /** Uniqueness Index over (metadataDescriptionId,locale,settingName) (database name metadata_descripton_settings_pkey) */
    val index2 = index("metadata_descripton_settings_pkey", (metadataDescriptionId, locale, settingName), unique=true)
  }
  /** Collection-like TableQuery object for table MetadataDescriptionSettings */
  lazy val MetadataDescriptionSettings = new TableQuery(tag => new MetadataDescriptionSettings(tag))
  
  /** Entity class storing rows of table Notes
   *  @param noteId Database column note_id AutoInc, PrimaryKey
   *  @param assocType Database column assoc_type 
   *  @param assocId Database column assoc_id 
   *  @param userId Database column user_id 
   *  @param dateCreated Database column date_created 
   *  @param dateModified Database column date_modified 
   *  @param title Database column title 
   *  @param fileId Database column file_id 
   *  @param contextId Database column context_id 
   *  @param contents Database column contents  */
  case class NotesRow(noteId: Long, assocType: Option[Short], assocId: Option[Long], userId: Long, dateCreated: java.sql.Timestamp, dateModified: Option[java.sql.Timestamp], title: Option[String], fileId: Option[Long], contextId: Option[Long], contents: Option[String])
  /** GetResult implicit for fetching NotesRow objects using plain SQL queries */
  implicit def GetResultNotesRow(implicit e0: GR[Long], e1: GR[Option[Short]], e2: GR[Option[Long]], e3: GR[java.sql.Timestamp], e4: GR[Option[java.sql.Timestamp]], e5: GR[Option[String]]): GR[NotesRow] = GR{
    prs => NotesRow.tupled((<<[Long], <<?[Short], <<?[Long], <<[Long], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String], <<?[Long], <<?[Long], <<?[String]))
  }
  /** Table description of table notes. Objects of this class serve as prototypes for rows in queries. */
  class Notes(tag: Tag) extends Table[NotesRow](tag, "notes") {
    def * = (noteId, assocType, assocId, userId, dateCreated, dateModified, title, fileId, contextId, contents) <> (NotesRow.tupled, NotesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (noteId.?, assocType, assocId, userId.?, dateCreated.?, dateModified, title, fileId, contextId, contents).shaped.<>({r=> _1.map(_=> NotesRow.tupled((_1.get, _2, _3, _4.get, _5.get, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column note_id AutoInc, PrimaryKey */
    val noteId: Column[Long] = column[Long]("note_id", O.AutoInc, O.PrimaryKey)
    /** Database column assoc_type  */
    val assocType: Column[Option[Short]] = column[Option[Short]]("assoc_type")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column date_created  */
    val dateCreated: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_created")
    /** Database column date_modified  */
    val dateModified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_modified")
    /** Database column title  */
    val title: Column[Option[String]] = column[Option[String]]("title")
    /** Database column file_id  */
    val fileId: Column[Option[Long]] = column[Option[Long]]("file_id")
    /** Database column context_id  */
    val contextId: Column[Option[Long]] = column[Option[Long]]("context_id")
    /** Database column contents  */
    val contents: Column[Option[String]] = column[Option[String]]("contents")
    
    /** Index over (assocType,assocId) (database name notes_assoc) */
    val index1 = index("notes_assoc", (assocType, assocId))
  }
  /** Collection-like TableQuery object for table Notes */
  lazy val Notes = new TableQuery(tag => new Notes(tag))
  
  /** Entity class storing rows of table Notifications
   *  @param notificationId Database column notification_id AutoInc, PrimaryKey
   *  @param userId Database column user_id 
   *  @param level Database column level 
   *  @param dateCreated Database column date_created 
   *  @param dateRead Database column date_read 
   *  @param title Database column title 
   *  @param contents Database column contents 
   *  @param param Database column param 
   *  @param location Database column location 
   *  @param isLocalized Database column is_localized 
   *  @param product Database column product 
   *  @param context Database column context 
   *  @param assocType Database column assoc_type  */
  case class NotificationsRow(notificationId: Long, userId: Long, level: Option[Long], dateCreated: java.sql.Timestamp, dateRead: Option[java.sql.Timestamp], title: Option[String], contents: Option[String], param: Option[String], location: Option[String], isLocalized: Byte, product: Option[String], context: Long, assocType: Long)
  /** GetResult implicit for fetching NotificationsRow objects using plain SQL queries */
  implicit def GetResultNotificationsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[java.sql.Timestamp], e3: GR[Option[java.sql.Timestamp]], e4: GR[Option[String]], e5: GR[Byte]): GR[NotificationsRow] = GR{
    prs => NotificationsRow.tupled((<<[Long], <<[Long], <<?[Long], <<[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String], <<[Byte], <<?[String], <<[Long], <<[Long]))
  }
  /** Table description of table notifications. Objects of this class serve as prototypes for rows in queries. */
  class Notifications(tag: Tag) extends Table[NotificationsRow](tag, "notifications") {
    def * = (notificationId, userId, level, dateCreated, dateRead, title, contents, param, location, isLocalized, product, context, assocType) <> (NotificationsRow.tupled, NotificationsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (notificationId.?, userId.?, level, dateCreated.?, dateRead, title, contents, param, location, isLocalized.?, product, context.?, assocType.?).shaped.<>({r=> _1.map(_=> NotificationsRow.tupled((_1.get, _2.get, _3, _4.get, _5, _6, _7, _8, _9, _10.get, _11, _12.get, _13.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column notification_id AutoInc, PrimaryKey */
    val notificationId: Column[Long] = column[Long]("notification_id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column level  */
    val level: Column[Option[Long]] = column[Option[Long]]("level")
    /** Database column date_created  */
    val dateCreated: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_created")
    /** Database column date_read  */
    val dateRead: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_read")
    /** Database column title  */
    val title: Column[Option[String]] = column[Option[String]]("title")
    /** Database column contents  */
    val contents: Column[Option[String]] = column[Option[String]]("contents")
    /** Database column param  */
    val param: Column[Option[String]] = column[Option[String]]("param")
    /** Database column location  */
    val location: Column[Option[String]] = column[Option[String]]("location")
    /** Database column is_localized  */
    val isLocalized: Column[Byte] = column[Byte]("is_localized")
    /** Database column product  */
    val product: Column[Option[String]] = column[Option[String]]("product")
    /** Database column context  */
    val context: Column[Long] = column[Long]("context")
    /** Database column assoc_type  */
    val assocType: Column[Long] = column[Long]("assoc_type")
    
    /** Index over (product,userId,level,context) (database name notifications_by_user) */
    val index1 = index("notifications_by_user", (product, userId, level, context))
  }
  /** Collection-like TableQuery object for table Notifications */
  lazy val Notifications = new TableQuery(tag => new Notifications(tag))
  
  /** Entity class storing rows of table NotificationSettings
   *  @param settingId Database column setting_id AutoInc, PrimaryKey
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param userId Database column user_id 
   *  @param product Database column product 
   *  @param context Database column context  */
  case class NotificationSettingsRow(settingId: Long, settingName: String, settingValue: Option[String], userId: Long, product: Option[String], context: Long)
  /** GetResult implicit for fetching NotificationSettingsRow objects using plain SQL queries */
  implicit def GetResultNotificationSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[NotificationSettingsRow] = GR{
    prs => NotificationSettingsRow.tupled((<<[Long], <<[String], <<?[String], <<[Long], <<?[String], <<[Long]))
  }
  /** Table description of table notification_settings. Objects of this class serve as prototypes for rows in queries. */
  class NotificationSettings(tag: Tag) extends Table[NotificationSettingsRow](tag, "notification_settings") {
    def * = (settingId, settingName, settingValue, userId, product, context) <> (NotificationSettingsRow.tupled, NotificationSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (settingId.?, settingName.?, settingValue, userId.?, product, context.?).shaped.<>({r=> _1.map(_=> NotificationSettingsRow.tupled((_1.get, _2.get, _3, _4.get, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column setting_id AutoInc, PrimaryKey */
    val settingId: Column[Long] = column[Long]("setting_id", O.AutoInc, O.PrimaryKey)
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column product  */
    val product: Column[Option[String]] = column[Option[String]]("product")
    /** Database column context  */
    val context: Column[Long] = column[Long]("context")
  }
  /** Collection-like TableQuery object for table NotificationSettings */
  lazy val NotificationSettings = new TableQuery(tag => new NotificationSettings(tag))
  
  /** Entity class storing rows of table OaiResumptionTokens
   *  @param token Database column token 
   *  @param expire Database column expire 
   *  @param recordOffset Database column record_offset 
   *  @param params Database column params  */
  case class OaiResumptionTokensRow(token: String, expire: Long, recordOffset: Int, params: Option[String])
  /** GetResult implicit for fetching OaiResumptionTokensRow objects using plain SQL queries */
  implicit def GetResultOaiResumptionTokensRow(implicit e0: GR[String], e1: GR[Long], e2: GR[Int], e3: GR[Option[String]]): GR[OaiResumptionTokensRow] = GR{
    prs => OaiResumptionTokensRow.tupled((<<[String], <<[Long], <<[Int], <<?[String]))
  }
  /** Table description of table oai_resumption_tokens. Objects of this class serve as prototypes for rows in queries. */
  class OaiResumptionTokens(tag: Tag) extends Table[OaiResumptionTokensRow](tag, "oai_resumption_tokens") {
    def * = (token, expire, recordOffset, params) <> (OaiResumptionTokensRow.tupled, OaiResumptionTokensRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (token.?, expire.?, recordOffset.?, params).shaped.<>({r=> _1.map(_=> OaiResumptionTokensRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column token  */
    val token: Column[String] = column[String]("token")
    /** Database column expire  */
    val expire: Column[Long] = column[Long]("expire")
    /** Database column record_offset  */
    val recordOffset: Column[Int] = column[Int]("record_offset")
    /** Database column params  */
    val params: Column[Option[String]] = column[Option[String]]("params")
    
    /** Uniqueness Index over (token) (database name oai_resumption_tokens_pkey) */
    val index1 = index("oai_resumption_tokens_pkey", token, unique=true)
  }
  /** Collection-like TableQuery object for table OaiResumptionTokens */
  lazy val OaiResumptionTokens = new TableQuery(tag => new OaiResumptionTokens(tag))
  
  /** Entity class storing rows of table PaypalTransactions
   *  @param txnId Database column txn_id PrimaryKey
   *  @param txnType Database column txn_type 
   *  @param payerEmail Database column payer_email 
   *  @param receiverEmail Database column receiver_email 
   *  @param itemNumber Database column item_number 
   *  @param paymentDate Database column payment_date 
   *  @param payerId Database column payer_id 
   *  @param receiverId Database column receiver_id  */
  case class PaypalTransactionsRow(txnId: String, txnType: Option[String], payerEmail: Option[String], receiverEmail: Option[String], itemNumber: Option[String], paymentDate: Option[String], payerId: Option[String], receiverId: Option[String])
  /** GetResult implicit for fetching PaypalTransactionsRow objects using plain SQL queries */
  implicit def GetResultPaypalTransactionsRow(implicit e0: GR[String], e1: GR[Option[String]]): GR[PaypalTransactionsRow] = GR{
    prs => PaypalTransactionsRow.tupled((<<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table paypal_transactions. Objects of this class serve as prototypes for rows in queries. */
  class PaypalTransactions(tag: Tag) extends Table[PaypalTransactionsRow](tag, "paypal_transactions") {
    def * = (txnId, txnType, payerEmail, receiverEmail, itemNumber, paymentDate, payerId, receiverId) <> (PaypalTransactionsRow.tupled, PaypalTransactionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (txnId.?, txnType, payerEmail, receiverEmail, itemNumber, paymentDate, payerId, receiverId).shaped.<>({r=> _1.map(_=> PaypalTransactionsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column txn_id PrimaryKey */
    val txnId: Column[String] = column[String]("txn_id", O.PrimaryKey)
    /** Database column txn_type  */
    val txnType: Column[Option[String]] = column[Option[String]]("txn_type")
    /** Database column payer_email  */
    val payerEmail: Column[Option[String]] = column[Option[String]]("payer_email")
    /** Database column receiver_email  */
    val receiverEmail: Column[Option[String]] = column[Option[String]]("receiver_email")
    /** Database column item_number  */
    val itemNumber: Column[Option[String]] = column[Option[String]]("item_number")
    /** Database column payment_date  */
    val paymentDate: Column[Option[String]] = column[Option[String]]("payment_date")
    /** Database column payer_id  */
    val payerId: Column[Option[String]] = column[Option[String]]("payer_id")
    /** Database column receiver_id  */
    val receiverId: Column[Option[String]] = column[Option[String]]("receiver_id")
  }
  /** Collection-like TableQuery object for table PaypalTransactions */
  lazy val PaypalTransactions = new TableQuery(tag => new PaypalTransactions(tag))
  
  /** Entity class storing rows of table PluginSettings
   *  @param pluginName Database column plugin_name 
   *  @param locale Database column locale 
   *  @param journalId Database column journal_id 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class PluginSettingsRow(pluginName: String, locale: String, journalId: Long, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching PluginSettingsRow objects using plain SQL queries */
  implicit def GetResultPluginSettingsRow(implicit e0: GR[String], e1: GR[Long], e2: GR[Option[String]]): GR[PluginSettingsRow] = GR{
    prs => PluginSettingsRow.tupled((<<[String], <<[String], <<[Long], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table plugin_settings. Objects of this class serve as prototypes for rows in queries. */
  class PluginSettings(tag: Tag) extends Table[PluginSettingsRow](tag, "plugin_settings") {
    def * = (pluginName, locale, journalId, settingName, settingValue, settingType) <> (PluginSettingsRow.tupled, PluginSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (pluginName.?, locale.?, journalId.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> PluginSettingsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column plugin_name  */
    val pluginName: Column[String] = column[String]("plugin_name")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (pluginName,locale,journalId,settingName) (database name plugin_settings_pkey) */
    val index1 = index("plugin_settings_pkey", (pluginName, locale, journalId, settingName), unique=true)
    /** Index over (pluginName) (database name plugin_settings_plugin_name) */
    val index2 = index("plugin_settings_plugin_name", pluginName)
  }
  /** Collection-like TableQuery object for table PluginSettings */
  lazy val PluginSettings = new TableQuery(tag => new PluginSettings(tag))
  
  /** Entity class storing rows of table Processes
   *  @param processId Database column process_id 
   *  @param processType Database column process_type 
   *  @param timeStarted Database column time_started 
   *  @param obliterated Database column obliterated  */
  case class ProcessesRow(processId: String, processType: Byte, timeStarted: Long, obliterated: Byte)
  /** GetResult implicit for fetching ProcessesRow objects using plain SQL queries */
  implicit def GetResultProcessesRow(implicit e0: GR[String], e1: GR[Byte], e2: GR[Long]): GR[ProcessesRow] = GR{
    prs => ProcessesRow.tupled((<<[String], <<[Byte], <<[Long], <<[Byte]))
  }
  /** Table description of table processes. Objects of this class serve as prototypes for rows in queries. */
  class Processes(tag: Tag) extends Table[ProcessesRow](tag, "processes") {
    def * = (processId, processType, timeStarted, obliterated) <> (ProcessesRow.tupled, ProcessesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (processId.?, processType.?, timeStarted.?, obliterated.?).shaped.<>({r=> _1.map(_=> ProcessesRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column process_id  */
    val processId: Column[String] = column[String]("process_id")
    /** Database column process_type  */
    val processType: Column[Byte] = column[Byte]("process_type")
    /** Database column time_started  */
    val timeStarted: Column[Long] = column[Long]("time_started")
    /** Database column obliterated  */
    val obliterated: Column[Byte] = column[Byte]("obliterated")
    
    /** Uniqueness Index over (processId) (database name processes_pkey) */
    val index1 = index("processes_pkey", processId, unique=true)
  }
  /** Collection-like TableQuery object for table Processes */
  lazy val Processes = new TableQuery(tag => new Processes(tag))
  
  /** Entity class storing rows of table PublishedArticles
   *  @param pubId Database column pub_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param issueId Database column issue_id 
   *  @param datePublished Database column date_published 
   *  @param seq Database column seq Default(0.0)
   *  @param views Database column views Default(0)
   *  @param accessStatus Database column access_status 
   *  @param publicArticleId Database column public_article_id  */
  case class PublishedArticlesRow(pubId: Long, articleId: Long, issueId: Long, datePublished: Option[java.sql.Timestamp], seq: Double = 0.0, views: Int = 0, accessStatus: Byte, publicArticleId: Option[String])
  /** GetResult implicit for fetching PublishedArticlesRow objects using plain SQL queries */
  implicit def GetResultPublishedArticlesRow(implicit e0: GR[Long], e1: GR[Option[java.sql.Timestamp]], e2: GR[Double], e3: GR[Int], e4: GR[Byte], e5: GR[Option[String]]): GR[PublishedArticlesRow] = GR{
    prs => PublishedArticlesRow.tupled((<<[Long], <<[Long], <<[Long], <<?[java.sql.Timestamp], <<[Double], <<[Int], <<[Byte], <<?[String]))
  }
  /** Table description of table published_articles. Objects of this class serve as prototypes for rows in queries. */
  class PublishedArticles(tag: Tag) extends Table[PublishedArticlesRow](tag, "published_articles") {
    def * = (pubId, articleId, issueId, datePublished, seq, views, accessStatus, publicArticleId) <> (PublishedArticlesRow.tupled, PublishedArticlesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (pubId.?, articleId.?, issueId.?, datePublished, seq.?, views.?, accessStatus.?, publicArticleId).shaped.<>({r=> _1.map(_=> PublishedArticlesRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6.get, _7.get, _8)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column pub_id AutoInc, PrimaryKey */
    val pubId: Column[Long] = column[Long]("pub_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column issue_id  */
    val issueId: Column[Long] = column[Long]("issue_id")
    /** Database column date_published  */
    val datePublished: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_published")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    /** Database column views Default(0) */
    val views: Column[Int] = column[Int]("views", O.Default(0))
    /** Database column access_status  */
    val accessStatus: Column[Byte] = column[Byte]("access_status")
    /** Database column public_article_id  */
    val publicArticleId: Column[Option[String]] = column[Option[String]]("public_article_id")
    
    /** Uniqueness Index over (articleId) (database name published_articles_article_id) */
    val index1 = index("published_articles_article_id", articleId, unique=true)
    /** Index over (issueId) (database name published_articles_issue_id) */
    val index2 = index("published_articles_issue_id", issueId)
    /** Index over (publicArticleId) (database name published_articles_public_article_id) */
    val index3 = index("published_articles_public_article_id", publicArticleId)
  }
  /** Collection-like TableQuery object for table PublishedArticles */
  lazy val PublishedArticles = new TableQuery(tag => new PublishedArticles(tag))
  
  /** Entity class storing rows of table QueuedPayments
   *  @param queuedPaymentId Database column queued_payment_id AutoInc, PrimaryKey
   *  @param dateCreated Database column date_created 
   *  @param dateModified Database column date_modified 
   *  @param expiryDate Database column expiry_date 
   *  @param paymentData Database column payment_data  */
  case class QueuedPaymentsRow(queuedPaymentId: Long, dateCreated: java.sql.Timestamp, dateModified: java.sql.Timestamp, expiryDate: Option[java.sql.Date], paymentData: Option[String])
  /** GetResult implicit for fetching QueuedPaymentsRow objects using plain SQL queries */
  implicit def GetResultQueuedPaymentsRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp], e2: GR[Option[java.sql.Date]], e3: GR[Option[String]]): GR[QueuedPaymentsRow] = GR{
    prs => QueuedPaymentsRow.tupled((<<[Long], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<?[java.sql.Date], <<?[String]))
  }
  /** Table description of table queued_payments. Objects of this class serve as prototypes for rows in queries. */
  class QueuedPayments(tag: Tag) extends Table[QueuedPaymentsRow](tag, "queued_payments") {
    def * = (queuedPaymentId, dateCreated, dateModified, expiryDate, paymentData) <> (QueuedPaymentsRow.tupled, QueuedPaymentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (queuedPaymentId.?, dateCreated.?, dateModified.?, expiryDate, paymentData).shaped.<>({r=> _1.map(_=> QueuedPaymentsRow.tupled((_1.get, _2.get, _3.get, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column queued_payment_id AutoInc, PrimaryKey */
    val queuedPaymentId: Column[Long] = column[Long]("queued_payment_id", O.AutoInc, O.PrimaryKey)
    /** Database column date_created  */
    val dateCreated: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_created")
    /** Database column date_modified  */
    val dateModified: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_modified")
    /** Database column expiry_date  */
    val expiryDate: Column[Option[java.sql.Date]] = column[Option[java.sql.Date]]("expiry_date")
    /** Database column payment_data  */
    val paymentData: Column[Option[String]] = column[Option[String]]("payment_data")
  }
  /** Collection-like TableQuery object for table QueuedPayments */
  lazy val QueuedPayments = new TableQuery(tag => new QueuedPayments(tag))
  
  /** Entity class storing rows of table Referrals
   *  @param referralId Database column referral_id AutoInc, PrimaryKey
   *  @param articleId Database column article_id 
   *  @param status Database column status 
   *  @param url Database column url 
   *  @param dateAdded Database column date_added 
   *  @param linkCount Database column link_count  */
  case class ReferralsRow(referralId: Long, articleId: Long, status: Short, url: String, dateAdded: java.sql.Timestamp, linkCount: Long)
  /** GetResult implicit for fetching ReferralsRow objects using plain SQL queries */
  implicit def GetResultReferralsRow(implicit e0: GR[Long], e1: GR[Short], e2: GR[String], e3: GR[java.sql.Timestamp]): GR[ReferralsRow] = GR{
    prs => ReferralsRow.tupled((<<[Long], <<[Long], <<[Short], <<[String], <<[java.sql.Timestamp], <<[Long]))
  }
  /** Table description of table referrals. Objects of this class serve as prototypes for rows in queries. */
  class Referrals(tag: Tag) extends Table[ReferralsRow](tag, "referrals") {
    def * = (referralId, articleId, status, url, dateAdded, linkCount) <> (ReferralsRow.tupled, ReferralsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (referralId.?, articleId.?, status.?, url.?, dateAdded.?, linkCount.?).shaped.<>({r=> _1.map(_=> ReferralsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column referral_id AutoInc, PrimaryKey */
    val referralId: Column[Long] = column[Long]("referral_id", O.AutoInc, O.PrimaryKey)
    /** Database column article_id  */
    val articleId: Column[Long] = column[Long]("article_id")
    /** Database column status  */
    val status: Column[Short] = column[Short]("status")
    /** Database column url  */
    val url: Column[String] = column[String]("url")
    /** Database column date_added  */
    val dateAdded: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_added")
    /** Database column link_count  */
    val linkCount: Column[Long] = column[Long]("link_count")
    
    /** Uniqueness Index over (articleId,url) (database name referral_article_id) */
    val index1 = index("referral_article_id", (articleId, url), unique=true)
  }
  /** Collection-like TableQuery object for table Referrals */
  lazy val Referrals = new TableQuery(tag => new Referrals(tag))
  
  /** Entity class storing rows of table ReferralSettings
   *  @param referralId Database column referral_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class ReferralSettingsRow(referralId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching ReferralSettingsRow objects using plain SQL queries */
  implicit def GetResultReferralSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ReferralSettingsRow] = GR{
    prs => ReferralSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table referral_settings. Objects of this class serve as prototypes for rows in queries. */
  class ReferralSettings(tag: Tag) extends Table[ReferralSettingsRow](tag, "referral_settings") {
    def * = (referralId, locale, settingName, settingValue, settingType) <> (ReferralSettingsRow.tupled, ReferralSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (referralId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> ReferralSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column referral_id  */
    val referralId: Column[Long] = column[Long]("referral_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (referralId,locale,settingName) (database name referral_settings_pkey) */
    val index1 = index("referral_settings_pkey", (referralId, locale, settingName), unique=true)
    /** Index over (referralId) (database name referral_settings_referral_id) */
    val index2 = index("referral_settings_referral_id", referralId)
  }
  /** Collection-like TableQuery object for table ReferralSettings */
  lazy val ReferralSettings = new TableQuery(tag => new ReferralSettings(tag))
  
  /** Row type of table ReviewAssignments */
  type ReviewAssignmentsRow = HCons[Long,HCons[Long,HCons[Long,HCons[Option[String],HCons[Option[String],HCons[Option[Byte],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Byte,HCons[Byte,HCons[Byte,HCons[Byte,HCons[Option[Long],HCons[Option[java.sql.Timestamp],HCons[Option[java.sql.Timestamp],HCons[Option[Byte],HCons[Byte,HCons[Byte,HCons[Byte,HCons[Byte,HCons[Option[Long],HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for ReviewAssignmentsRow providing default values if available in the database schema. */
  def ReviewAssignmentsRow(reviewId: Long, submissionId: Long, reviewerId: Long, competingInterests: Option[String], regretMessage: Option[String], recommendation: Option[Byte], dateAssigned: Option[java.sql.Timestamp], dateNotified: Option[java.sql.Timestamp], dateConfirmed: Option[java.sql.Timestamp], dateCompleted: Option[java.sql.Timestamp], dateAcknowledged: Option[java.sql.Timestamp], dateDue: Option[java.sql.Timestamp], dateResponseDue: Option[java.sql.Timestamp], lastModified: Option[java.sql.Timestamp], reminderWasAutomatic: Byte, declined: Byte, replaced: Byte, cancelled: Byte, reviewerFileId: Option[Long], dateRated: Option[java.sql.Timestamp], dateReminded: Option[java.sql.Timestamp], quality: Option[Byte], reviewType: Byte, reviewMethod: Byte, round: Byte, step: Byte, reviewFormId: Option[Long]): ReviewAssignmentsRow = {
    reviewId :: submissionId :: reviewerId :: competingInterests :: regretMessage :: recommendation :: dateAssigned :: dateNotified :: dateConfirmed :: dateCompleted :: dateAcknowledged :: dateDue :: dateResponseDue :: lastModified :: reminderWasAutomatic :: declined :: replaced :: cancelled :: reviewerFileId :: dateRated :: dateReminded :: quality :: reviewType :: reviewMethod :: round :: step :: reviewFormId :: HNil
  }
  /** GetResult implicit for fetching ReviewAssignmentsRow objects using plain SQL queries */
  implicit def GetResultReviewAssignmentsRow(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Byte]], e3: GR[Option[java.sql.Timestamp]], e4: GR[Byte], e5: GR[Option[Long]]): GR[ReviewAssignmentsRow] = GR{
    prs => <<[Long] :: <<[Long] :: <<[Long] :: <<?[String] :: <<?[String] :: <<?[Byte] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<[Byte] :: <<[Byte] :: <<[Byte] :: <<[Byte] :: <<?[Long] :: <<?[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<?[Byte] :: <<[Byte] :: <<[Byte] :: <<[Byte] :: <<[Byte] :: <<?[Long] :: HNil
  }
  /** Table description of table review_assignments. Objects of this class serve as prototypes for rows in queries. */
  class ReviewAssignments(tag: Tag) extends Table[ReviewAssignmentsRow](tag, "review_assignments") {
    def * = reviewId :: submissionId :: reviewerId :: competingInterests :: regretMessage :: recommendation :: dateAssigned :: dateNotified :: dateConfirmed :: dateCompleted :: dateAcknowledged :: dateDue :: dateResponseDue :: lastModified :: reminderWasAutomatic :: declined :: replaced :: cancelled :: reviewerFileId :: dateRated :: dateReminded :: quality :: reviewType :: reviewMethod :: round :: step :: reviewFormId :: HNil
    
    /** Database column review_id AutoInc, PrimaryKey */
    val reviewId: Column[Long] = column[Long]("review_id", O.AutoInc, O.PrimaryKey)
    /** Database column submission_id  */
    val submissionId: Column[Long] = column[Long]("submission_id")
    /** Database column reviewer_id  */
    val reviewerId: Column[Long] = column[Long]("reviewer_id")
    /** Database column competing_interests  */
    val competingInterests: Column[Option[String]] = column[Option[String]]("competing_interests")
    /** Database column regret_message  */
    val regretMessage: Column[Option[String]] = column[Option[String]]("regret_message")
    /** Database column recommendation  */
    val recommendation: Column[Option[Byte]] = column[Option[Byte]]("recommendation")
    /** Database column date_assigned  */
    val dateAssigned: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_assigned")
    /** Database column date_notified  */
    val dateNotified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_notified")
    /** Database column date_confirmed  */
    val dateConfirmed: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_confirmed")
    /** Database column date_completed  */
    val dateCompleted: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_completed")
    /** Database column date_acknowledged  */
    val dateAcknowledged: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_acknowledged")
    /** Database column date_due  */
    val dateDue: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_due")
    /** Database column date_response_due  */
    val dateResponseDue: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_response_due")
    /** Database column last_modified  */
    val lastModified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_modified")
    /** Database column reminder_was_automatic  */
    val reminderWasAutomatic: Column[Byte] = column[Byte]("reminder_was_automatic")
    /** Database column declined  */
    val declined: Column[Byte] = column[Byte]("declined")
    /** Database column replaced  */
    val replaced: Column[Byte] = column[Byte]("replaced")
    /** Database column cancelled  */
    val cancelled: Column[Byte] = column[Byte]("cancelled")
    /** Database column reviewer_file_id  */
    val reviewerFileId: Column[Option[Long]] = column[Option[Long]]("reviewer_file_id")
    /** Database column date_rated  */
    val dateRated: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_rated")
    /** Database column date_reminded  */
    val dateReminded: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_reminded")
    /** Database column quality  */
    val quality: Column[Option[Byte]] = column[Option[Byte]]("quality")
    /** Database column review_type  */
    val reviewType: Column[Byte] = column[Byte]("review_type")
    /** Database column review_method  */
    val reviewMethod: Column[Byte] = column[Byte]("review_method")
    /** Database column round  */
    val round: Column[Byte] = column[Byte]("round")
    /** Database column step  */
    val step: Column[Byte] = column[Byte]("step")
    /** Database column review_form_id  */
    val reviewFormId: Column[Option[Long]] = column[Option[Long]]("review_form_id")
    
    /** Index over (reviewFormId) (database name review_assignments_form_id) */
    val index1 = index("review_assignments_form_id", reviewFormId :: HNil)
    /** Index over (reviewerId) (database name review_assignments_reviewer_id) */
    val index2 = index("review_assignments_reviewer_id", reviewerId :: HNil)
    /** Index over (submissionId) (database name review_assignments_submission_id) */
    val index3 = index("review_assignments_submission_id", submissionId :: HNil)
  }
  /** Collection-like TableQuery object for table ReviewAssignments */
  lazy val ReviewAssignments = new TableQuery(tag => new ReviewAssignments(tag))
  
  /** Entity class storing rows of table ReviewFormElements
   *  @param reviewFormElementId Database column review_form_element_id AutoInc, PrimaryKey
   *  @param reviewFormId Database column review_form_id 
   *  @param seq Database column seq 
   *  @param elementType Database column element_type 
   *  @param required Database column required 
   *  @param included Database column included  */
  case class ReviewFormElementsRow(reviewFormElementId: Long, reviewFormId: Long, seq: Option[Double], elementType: Option[Long], required: Option[Byte], included: Option[Byte])
  /** GetResult implicit for fetching ReviewFormElementsRow objects using plain SQL queries */
  implicit def GetResultReviewFormElementsRow(implicit e0: GR[Long], e1: GR[Option[Double]], e2: GR[Option[Long]], e3: GR[Option[Byte]]): GR[ReviewFormElementsRow] = GR{
    prs => ReviewFormElementsRow.tupled((<<[Long], <<[Long], <<?[Double], <<?[Long], <<?[Byte], <<?[Byte]))
  }
  /** Table description of table review_form_elements. Objects of this class serve as prototypes for rows in queries. */
  class ReviewFormElements(tag: Tag) extends Table[ReviewFormElementsRow](tag, "review_form_elements") {
    def * = (reviewFormElementId, reviewFormId, seq, elementType, required, included) <> (ReviewFormElementsRow.tupled, ReviewFormElementsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (reviewFormElementId.?, reviewFormId.?, seq, elementType, required, included).shaped.<>({r=> _1.map(_=> ReviewFormElementsRow.tupled((_1.get, _2.get, _3, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column review_form_element_id AutoInc, PrimaryKey */
    val reviewFormElementId: Column[Long] = column[Long]("review_form_element_id", O.AutoInc, O.PrimaryKey)
    /** Database column review_form_id  */
    val reviewFormId: Column[Long] = column[Long]("review_form_id")
    /** Database column seq  */
    val seq: Column[Option[Double]] = column[Option[Double]]("seq")
    /** Database column element_type  */
    val elementType: Column[Option[Long]] = column[Option[Long]]("element_type")
    /** Database column required  */
    val required: Column[Option[Byte]] = column[Option[Byte]]("required")
    /** Database column included  */
    val included: Column[Option[Byte]] = column[Option[Byte]]("included")
    
    /** Index over (reviewFormId) (database name review_form_elements_review_form_id) */
    val index1 = index("review_form_elements_review_form_id", reviewFormId)
  }
  /** Collection-like TableQuery object for table ReviewFormElements */
  lazy val ReviewFormElements = new TableQuery(tag => new ReviewFormElements(tag))
  
  /** Entity class storing rows of table ReviewFormElementSettings
   *  @param reviewFormElementId Database column review_form_element_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class ReviewFormElementSettingsRow(reviewFormElementId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching ReviewFormElementSettingsRow objects using plain SQL queries */
  implicit def GetResultReviewFormElementSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ReviewFormElementSettingsRow] = GR{
    prs => ReviewFormElementSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table review_form_element_settings. Objects of this class serve as prototypes for rows in queries. */
  class ReviewFormElementSettings(tag: Tag) extends Table[ReviewFormElementSettingsRow](tag, "review_form_element_settings") {
    def * = (reviewFormElementId, locale, settingName, settingValue, settingType) <> (ReviewFormElementSettingsRow.tupled, ReviewFormElementSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (reviewFormElementId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> ReviewFormElementSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column review_form_element_id  */
    val reviewFormElementId: Column[Long] = column[Long]("review_form_element_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (reviewFormElementId,locale,settingName) (database name review_form_element_settings_pkey) */
    val index1 = index("review_form_element_settings_pkey", (reviewFormElementId, locale, settingName), unique=true)
    /** Index over (reviewFormElementId) (database name review_form_element_settings_review_form_element_id) */
    val index2 = index("review_form_element_settings_review_form_element_id", reviewFormElementId)
  }
  /** Collection-like TableQuery object for table ReviewFormElementSettings */
  lazy val ReviewFormElementSettings = new TableQuery(tag => new ReviewFormElementSettings(tag))
  
  /** Entity class storing rows of table ReviewFormResponses
   *  @param reviewFormElementId Database column review_form_element_id 
   *  @param reviewId Database column review_id 
   *  @param responseType Database column response_type 
   *  @param responseValue Database column response_value  */
  case class ReviewFormResponsesRow(reviewFormElementId: Long, reviewId: Long, responseType: Option[String], responseValue: Option[String])
  /** GetResult implicit for fetching ReviewFormResponsesRow objects using plain SQL queries */
  implicit def GetResultReviewFormResponsesRow(implicit e0: GR[Long], e1: GR[Option[String]]): GR[ReviewFormResponsesRow] = GR{
    prs => ReviewFormResponsesRow.tupled((<<[Long], <<[Long], <<?[String], <<?[String]))
  }
  /** Table description of table review_form_responses. Objects of this class serve as prototypes for rows in queries. */
  class ReviewFormResponses(tag: Tag) extends Table[ReviewFormResponsesRow](tag, "review_form_responses") {
    def * = (reviewFormElementId, reviewId, responseType, responseValue) <> (ReviewFormResponsesRow.tupled, ReviewFormResponsesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (reviewFormElementId.?, reviewId.?, responseType, responseValue).shaped.<>({r=> _1.map(_=> ReviewFormResponsesRow.tupled((_1.get, _2.get, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column review_form_element_id  */
    val reviewFormElementId: Column[Long] = column[Long]("review_form_element_id")
    /** Database column review_id  */
    val reviewId: Column[Long] = column[Long]("review_id")
    /** Database column response_type  */
    val responseType: Column[Option[String]] = column[Option[String]]("response_type")
    /** Database column response_value  */
    val responseValue: Column[Option[String]] = column[Option[String]]("response_value")
    
    /** Index over (reviewFormElementId,reviewId) (database name review_form_responses_pkey) */
    val index1 = index("review_form_responses_pkey", (reviewFormElementId, reviewId))
  }
  /** Collection-like TableQuery object for table ReviewFormResponses */
  lazy val ReviewFormResponses = new TableQuery(tag => new ReviewFormResponses(tag))
  
  /** Entity class storing rows of table ReviewForms
   *  @param reviewFormId Database column review_form_id AutoInc, PrimaryKey
   *  @param assocType Database column assoc_type 
   *  @param assocId Database column assoc_id 
   *  @param seq Database column seq 
   *  @param isActive Database column is_active  */
  case class ReviewFormsRow(reviewFormId: Long, assocType: Option[Long], assocId: Option[Long], seq: Option[Double], isActive: Option[Byte])
  /** GetResult implicit for fetching ReviewFormsRow objects using plain SQL queries */
  implicit def GetResultReviewFormsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Option[Double]], e3: GR[Option[Byte]]): GR[ReviewFormsRow] = GR{
    prs => ReviewFormsRow.tupled((<<[Long], <<?[Long], <<?[Long], <<?[Double], <<?[Byte]))
  }
  /** Table description of table review_forms. Objects of this class serve as prototypes for rows in queries. */
  class ReviewForms(tag: Tag) extends Table[ReviewFormsRow](tag, "review_forms") {
    def * = (reviewFormId, assocType, assocId, seq, isActive) <> (ReviewFormsRow.tupled, ReviewFormsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (reviewFormId.?, assocType, assocId, seq, isActive).shaped.<>({r=> _1.map(_=> ReviewFormsRow.tupled((_1.get, _2, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column review_form_id AutoInc, PrimaryKey */
    val reviewFormId: Column[Long] = column[Long]("review_form_id", O.AutoInc, O.PrimaryKey)
    /** Database column assoc_type  */
    val assocType: Column[Option[Long]] = column[Option[Long]]("assoc_type")
    /** Database column assoc_id  */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id")
    /** Database column seq  */
    val seq: Column[Option[Double]] = column[Option[Double]]("seq")
    /** Database column is_active  */
    val isActive: Column[Option[Byte]] = column[Option[Byte]]("is_active")
  }
  /** Collection-like TableQuery object for table ReviewForms */
  lazy val ReviewForms = new TableQuery(tag => new ReviewForms(tag))
  
  /** Entity class storing rows of table ReviewFormSettings
   *  @param reviewFormId Database column review_form_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class ReviewFormSettingsRow(reviewFormId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching ReviewFormSettingsRow objects using plain SQL queries */
  implicit def GetResultReviewFormSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[ReviewFormSettingsRow] = GR{
    prs => ReviewFormSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table review_form_settings. Objects of this class serve as prototypes for rows in queries. */
  class ReviewFormSettings(tag: Tag) extends Table[ReviewFormSettingsRow](tag, "review_form_settings") {
    def * = (reviewFormId, locale, settingName, settingValue, settingType) <> (ReviewFormSettingsRow.tupled, ReviewFormSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (reviewFormId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> ReviewFormSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column review_form_id  */
    val reviewFormId: Column[Long] = column[Long]("review_form_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (reviewFormId,locale,settingName) (database name review_form_settings_pkey) */
    val index1 = index("review_form_settings_pkey", (reviewFormId, locale, settingName), unique=true)
    /** Index over (reviewFormId) (database name review_form_settings_review_form_id) */
    val index2 = index("review_form_settings_review_form_id", reviewFormId)
  }
  /** Collection-like TableQuery object for table ReviewFormSettings */
  lazy val ReviewFormSettings = new TableQuery(tag => new ReviewFormSettings(tag))
  
  /** Entity class storing rows of table ReviewRounds
   *  @param submissionId Database column submission_id 
   *  @param round Database column round 
   *  @param reviewRevision Database column review_revision 
   *  @param reviewType Database column review_type 
   *  @param status Database column status  */
  case class ReviewRoundsRow(submissionId: Long, round: Byte, reviewRevision: Option[Long], reviewType: Option[Long], status: Option[Long])
  /** GetResult implicit for fetching ReviewRoundsRow objects using plain SQL queries */
  implicit def GetResultReviewRoundsRow(implicit e0: GR[Long], e1: GR[Byte], e2: GR[Option[Long]]): GR[ReviewRoundsRow] = GR{
    prs => ReviewRoundsRow.tupled((<<[Long], <<[Byte], <<?[Long], <<?[Long], <<?[Long]))
  }
  /** Table description of table review_rounds. Objects of this class serve as prototypes for rows in queries. */
  class ReviewRounds(tag: Tag) extends Table[ReviewRoundsRow](tag, "review_rounds") {
    def * = (submissionId, round, reviewRevision, reviewType, status) <> (ReviewRoundsRow.tupled, ReviewRoundsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (submissionId.?, round.?, reviewRevision, reviewType, status).shaped.<>({r=> _1.map(_=> ReviewRoundsRow.tupled((_1.get, _2.get, _3, _4, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column submission_id  */
    val submissionId: Column[Long] = column[Long]("submission_id")
    /** Database column round  */
    val round: Column[Byte] = column[Byte]("round")
    /** Database column review_revision  */
    val reviewRevision: Column[Option[Long]] = column[Option[Long]]("review_revision")
    /** Database column review_type  */
    val reviewType: Column[Option[Long]] = column[Option[Long]]("review_type")
    /** Database column status  */
    val status: Column[Option[Long]] = column[Option[Long]]("status")
    
    /** Uniqueness Index over (submissionId,round,reviewType) (database name review_rounds_pkey) */
    val index1 = index("review_rounds_pkey", (submissionId, round, reviewType), unique=true)
    /** Index over (submissionId) (database name review_rounds_submission_id) */
    val index2 = index("review_rounds_submission_id", submissionId)
  }
  /** Collection-like TableQuery object for table ReviewRounds */
  lazy val ReviewRounds = new TableQuery(tag => new ReviewRounds(tag))
  
  /** Entity class storing rows of table Roles
   *  @param journalId Database column journal_id 
   *  @param userId Database column user_id 
   *  @param roleId Database column role_id  */
  case class RolesRow(journalId: Long, userId: Long, roleId: Long)
  /** GetResult implicit for fetching RolesRow objects using plain SQL queries */
  implicit def GetResultRolesRow(implicit e0: GR[Long]): GR[RolesRow] = GR{
    prs => RolesRow.tupled((<<[Long], <<[Long], <<[Long]))
  }
  /** Table description of table roles. Objects of this class serve as prototypes for rows in queries. */
  class Roles(tag: Tag) extends Table[RolesRow](tag, "roles") {
    def * = (journalId, userId, roleId) <> (RolesRow.tupled, RolesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (journalId.?, userId.?, roleId.?).shaped.<>({r=> _1.map(_=> RolesRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column role_id  */
    val roleId: Column[Long] = column[Long]("role_id")
    
    /** Index over (journalId) (database name roles_journal_id) */
    val index1 = index("roles_journal_id", journalId)
    /** Uniqueness Index over (journalId,userId,roleId) (database name roles_pkey) */
    val index2 = index("roles_pkey", (journalId, userId, roleId), unique=true)
    /** Index over (roleId) (database name roles_role_id) */
    val index3 = index("roles_role_id", roleId)
    /** Index over (userId) (database name roles_user_id) */
    val index4 = index("roles_user_id", userId)
  }
  /** Collection-like TableQuery object for table Roles */
  lazy val Roles = new TableQuery(tag => new Roles(tag))
  
  /** Entity class storing rows of table RtContexts
   *  @param contextId Database column context_id AutoInc, PrimaryKey
   *  @param versionId Database column version_id 
   *  @param title Database column title 
   *  @param abbrev Database column abbrev 
   *  @param description Database column description 
   *  @param citedBy Database column cited_by 
   *  @param authorTerms Database column author_terms 
   *  @param defineTerms Database column define_terms 
   *  @param geoTerms Database column geo_terms 
   *  @param seq Database column seq Default(0.0) */
  case class RtContextsRow(contextId: Long, versionId: Long, title: String, abbrev: String, description: Option[String], citedBy: Byte, authorTerms: Byte, defineTerms: Byte, geoTerms: Byte, seq: Double = 0.0)
  /** GetResult implicit for fetching RtContextsRow objects using plain SQL queries */
  implicit def GetResultRtContextsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Byte], e4: GR[Double]): GR[RtContextsRow] = GR{
    prs => RtContextsRow.tupled((<<[Long], <<[Long], <<[String], <<[String], <<?[String], <<[Byte], <<[Byte], <<[Byte], <<[Byte], <<[Double]))
  }
  /** Table description of table rt_contexts. Objects of this class serve as prototypes for rows in queries. */
  class RtContexts(tag: Tag) extends Table[RtContextsRow](tag, "rt_contexts") {
    def * = (contextId, versionId, title, abbrev, description, citedBy, authorTerms, defineTerms, geoTerms, seq) <> (RtContextsRow.tupled, RtContextsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (contextId.?, versionId.?, title.?, abbrev.?, description, citedBy.?, authorTerms.?, defineTerms.?, geoTerms.?, seq.?).shaped.<>({r=> _1.map(_=> RtContextsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get, _7.get, _8.get, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column context_id AutoInc, PrimaryKey */
    val contextId: Column[Long] = column[Long]("context_id", O.AutoInc, O.PrimaryKey)
    /** Database column version_id  */
    val versionId: Column[Long] = column[Long]("version_id")
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column abbrev  */
    val abbrev: Column[String] = column[String]("abbrev")
    /** Database column description  */
    val description: Column[Option[String]] = column[Option[String]]("description")
    /** Database column cited_by  */
    val citedBy: Column[Byte] = column[Byte]("cited_by")
    /** Database column author_terms  */
    val authorTerms: Column[Byte] = column[Byte]("author_terms")
    /** Database column define_terms  */
    val defineTerms: Column[Byte] = column[Byte]("define_terms")
    /** Database column geo_terms  */
    val geoTerms: Column[Byte] = column[Byte]("geo_terms")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    
    /** Index over (versionId) (database name rt_contexts_version_id) */
    val index1 = index("rt_contexts_version_id", versionId)
  }
  /** Collection-like TableQuery object for table RtContexts */
  lazy val RtContexts = new TableQuery(tag => new RtContexts(tag))
  
  /** Entity class storing rows of table RtSearches
   *  @param searchId Database column search_id AutoInc, PrimaryKey
   *  @param contextId Database column context_id 
   *  @param title Database column title 
   *  @param description Database column description 
   *  @param url Database column url 
   *  @param searchUrl Database column search_url 
   *  @param searchPost Database column search_post 
   *  @param seq Database column seq Default(0.0) */
  case class RtSearchesRow(searchId: Long, contextId: Long, title: String, description: Option[String], url: Option[String], searchUrl: Option[String], searchPost: Option[String], seq: Double = 0.0)
  /** GetResult implicit for fetching RtSearchesRow objects using plain SQL queries */
  implicit def GetResultRtSearchesRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Double]): GR[RtSearchesRow] = GR{
    prs => RtSearchesRow.tupled((<<[Long], <<[Long], <<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<[Double]))
  }
  /** Table description of table rt_searches. Objects of this class serve as prototypes for rows in queries. */
  class RtSearches(tag: Tag) extends Table[RtSearchesRow](tag, "rt_searches") {
    def * = (searchId, contextId, title, description, url, searchUrl, searchPost, seq) <> (RtSearchesRow.tupled, RtSearchesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (searchId.?, contextId.?, title.?, description, url, searchUrl, searchPost, seq.?).shaped.<>({r=> _1.map(_=> RtSearchesRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column search_id AutoInc, PrimaryKey */
    val searchId: Column[Long] = column[Long]("search_id", O.AutoInc, O.PrimaryKey)
    /** Database column context_id  */
    val contextId: Column[Long] = column[Long]("context_id")
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column description  */
    val description: Column[Option[String]] = column[Option[String]]("description")
    /** Database column url  */
    val url: Column[Option[String]] = column[Option[String]]("url")
    /** Database column search_url  */
    val searchUrl: Column[Option[String]] = column[Option[String]]("search_url")
    /** Database column search_post  */
    val searchPost: Column[Option[String]] = column[Option[String]]("search_post")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    
    /** Index over (contextId) (database name rt_searches_context_id) */
    val index1 = index("rt_searches_context_id", contextId)
  }
  /** Collection-like TableQuery object for table RtSearches */
  lazy val RtSearches = new TableQuery(tag => new RtSearches(tag))
  
  /** Entity class storing rows of table RtVersions
   *  @param versionId Database column version_id AutoInc, PrimaryKey
   *  @param journalId Database column journal_id 
   *  @param versionKey Database column version_key 
   *  @param locale Database column locale Default(None)
   *  @param title Database column title 
   *  @param description Database column description  */
  case class RtVersionsRow(versionId: Long, journalId: Long, versionKey: String, locale: Option[String] = None, title: String, description: Option[String])
  /** GetResult implicit for fetching RtVersionsRow objects using plain SQL queries */
  implicit def GetResultRtVersionsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[RtVersionsRow] = GR{
    prs => RtVersionsRow.tupled((<<[Long], <<[Long], <<[String], <<?[String], <<[String], <<?[String]))
  }
  /** Table description of table rt_versions. Objects of this class serve as prototypes for rows in queries. */
  class RtVersions(tag: Tag) extends Table[RtVersionsRow](tag, "rt_versions") {
    def * = (versionId, journalId, versionKey, locale, title, description) <> (RtVersionsRow.tupled, RtVersionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (versionId.?, journalId.?, versionKey.?, locale, title.?, description).shaped.<>({r=> _1.map(_=> RtVersionsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column version_id AutoInc, PrimaryKey */
    val versionId: Column[Long] = column[Long]("version_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column version_key  */
    val versionKey: Column[String] = column[String]("version_key")
    /** Database column locale Default(None) */
    val locale: Column[Option[String]] = column[Option[String]]("locale", O.Default(None))
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column description  */
    val description: Column[Option[String]] = column[Option[String]]("description")
    
    /** Index over (journalId) (database name rt_versions_journal_id) */
    val index1 = index("rt_versions_journal_id", journalId)
    /** Index over (versionKey) (database name rt_versions_version_key) */
    val index2 = index("rt_versions_version_key", versionKey)
  }
  /** Collection-like TableQuery object for table RtVersions */
  lazy val RtVersions = new TableQuery(tag => new RtVersions(tag))
  
  /** Entity class storing rows of table ScheduledTasks
   *  @param className Database column class_name 
   *  @param lastRun Database column last_run  */
  case class ScheduledTasksRow(className: String, lastRun: Option[java.sql.Timestamp])
  /** GetResult implicit for fetching ScheduledTasksRow objects using plain SQL queries */
  implicit def GetResultScheduledTasksRow(implicit e0: GR[String], e1: GR[Option[java.sql.Timestamp]]): GR[ScheduledTasksRow] = GR{
    prs => ScheduledTasksRow.tupled((<<[String], <<?[java.sql.Timestamp]))
  }
  /** Table description of table scheduled_tasks. Objects of this class serve as prototypes for rows in queries. */
  class ScheduledTasks(tag: Tag) extends Table[ScheduledTasksRow](tag, "scheduled_tasks") {
    def * = (className, lastRun) <> (ScheduledTasksRow.tupled, ScheduledTasksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (className.?, lastRun).shaped.<>({r=> _1.map(_=> ScheduledTasksRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column class_name  */
    val className: Column[String] = column[String]("class_name")
    /** Database column last_run  */
    val lastRun: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("last_run")
    
    /** Uniqueness Index over (className) (database name scheduled_tasks_pkey) */
    val index1 = index("scheduled_tasks_pkey", className, unique=true)
  }
  /** Collection-like TableQuery object for table ScheduledTasks */
  lazy val ScheduledTasks = new TableQuery(tag => new ScheduledTasks(tag))
  
  /** Entity class storing rows of table SectionEditors
   *  @param journalId Database column journal_id 
   *  @param sectionId Database column section_id 
   *  @param userId Database column user_id 
   *  @param canEdit Database column can_edit 
   *  @param canReview Database column can_review  */
  case class SectionEditorsRow(journalId: Long, sectionId: Long, userId: Long, canEdit: Byte, canReview: Byte)
  /** GetResult implicit for fetching SectionEditorsRow objects using plain SQL queries */
  implicit def GetResultSectionEditorsRow(implicit e0: GR[Long], e1: GR[Byte]): GR[SectionEditorsRow] = GR{
    prs => SectionEditorsRow.tupled((<<[Long], <<[Long], <<[Long], <<[Byte], <<[Byte]))
  }
  /** Table description of table section_editors. Objects of this class serve as prototypes for rows in queries. */
  class SectionEditors(tag: Tag) extends Table[SectionEditorsRow](tag, "section_editors") {
    def * = (journalId, sectionId, userId, canEdit, canReview) <> (SectionEditorsRow.tupled, SectionEditorsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (journalId.?, sectionId.?, userId.?, canEdit.?, canReview.?).shaped.<>({r=> _1.map(_=> SectionEditorsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column section_id  */
    val sectionId: Column[Long] = column[Long]("section_id")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column can_edit  */
    val canEdit: Column[Byte] = column[Byte]("can_edit")
    /** Database column can_review  */
    val canReview: Column[Byte] = column[Byte]("can_review")
    
    /** Index over (journalId) (database name section_editors_journal_id) */
    val index1 = index("section_editors_journal_id", journalId)
    /** Uniqueness Index over (journalId,sectionId,userId) (database name section_editors_pkey) */
    val index2 = index("section_editors_pkey", (journalId, sectionId, userId), unique=true)
    /** Index over (sectionId) (database name section_editors_section_id) */
    val index3 = index("section_editors_section_id", sectionId)
    /** Index over (userId) (database name section_editors_user_id) */
    val index4 = index("section_editors_user_id", userId)
  }
  /** Collection-like TableQuery object for table SectionEditors */
  lazy val SectionEditors = new TableQuery(tag => new SectionEditors(tag))
  
  /** Entity class storing rows of table Sections
   *  @param sectionId Database column section_id AutoInc, PrimaryKey
   *  @param journalId Database column journal_id 
   *  @param reviewFormId Database column review_form_id 
   *  @param seq Database column seq Default(0.0)
   *  @param editorRestricted Database column editor_restricted 
   *  @param metaIndexed Database column meta_indexed 
   *  @param metaReviewed Database column meta_reviewed 
   *  @param abstractsNotRequired Database column abstracts_not_required 
   *  @param hideTitle Database column hide_title 
   *  @param hideAuthor Database column hide_author 
   *  @param hideAbout Database column hide_about 
   *  @param disableComments Database column disable_comments 
   *  @param abstractWordCount Database column abstract_word_count  */
  case class SectionsRow(sectionId: Long, journalId: Long, reviewFormId: Option[Long], seq: Double = 0.0, editorRestricted: Byte, metaIndexed: Byte, metaReviewed: Byte, abstractsNotRequired: Byte, hideTitle: Byte, hideAuthor: Byte, hideAbout: Byte, disableComments: Byte, abstractWordCount: Option[Long])
  /** GetResult implicit for fetching SectionsRow objects using plain SQL queries */
  implicit def GetResultSectionsRow(implicit e0: GR[Long], e1: GR[Option[Long]], e2: GR[Double], e3: GR[Byte]): GR[SectionsRow] = GR{
    prs => SectionsRow.tupled((<<[Long], <<[Long], <<?[Long], <<[Double], <<[Byte], <<[Byte], <<[Byte], <<[Byte], <<[Byte], <<[Byte], <<[Byte], <<[Byte], <<?[Long]))
  }
  /** Table description of table sections. Objects of this class serve as prototypes for rows in queries. */
  class Sections(tag: Tag) extends Table[SectionsRow](tag, "sections") {
    def * = (sectionId, journalId, reviewFormId, seq, editorRestricted, metaIndexed, metaReviewed, abstractsNotRequired, hideTitle, hideAuthor, hideAbout, disableComments, abstractWordCount) <> (SectionsRow.tupled, SectionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (sectionId.?, journalId.?, reviewFormId, seq.?, editorRestricted.?, metaIndexed.?, metaReviewed.?, abstractsNotRequired.?, hideTitle.?, hideAuthor.?, hideAbout.?, disableComments.?, abstractWordCount).shaped.<>({r=> _1.map(_=> SectionsRow.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column section_id AutoInc, PrimaryKey */
    val sectionId: Column[Long] = column[Long]("section_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column review_form_id  */
    val reviewFormId: Column[Option[Long]] = column[Option[Long]]("review_form_id")
    /** Database column seq Default(0.0) */
    val seq: Column[Double] = column[Double]("seq", O.Default(0.0))
    /** Database column editor_restricted  */
    val editorRestricted: Column[Byte] = column[Byte]("editor_restricted")
    /** Database column meta_indexed  */
    val metaIndexed: Column[Byte] = column[Byte]("meta_indexed")
    /** Database column meta_reviewed  */
    val metaReviewed: Column[Byte] = column[Byte]("meta_reviewed")
    /** Database column abstracts_not_required  */
    val abstractsNotRequired: Column[Byte] = column[Byte]("abstracts_not_required")
    /** Database column hide_title  */
    val hideTitle: Column[Byte] = column[Byte]("hide_title")
    /** Database column hide_author  */
    val hideAuthor: Column[Byte] = column[Byte]("hide_author")
    /** Database column hide_about  */
    val hideAbout: Column[Byte] = column[Byte]("hide_about")
    /** Database column disable_comments  */
    val disableComments: Column[Byte] = column[Byte]("disable_comments")
    /** Database column abstract_word_count  */
    val abstractWordCount: Column[Option[Long]] = column[Option[Long]]("abstract_word_count")
    
    /** Index over (journalId) (database name sections_journal_id) */
    val index1 = index("sections_journal_id", journalId)
  }
  /** Collection-like TableQuery object for table Sections */
  lazy val Sections = new TableQuery(tag => new Sections(tag))
  
  /** Entity class storing rows of table SectionSettings
   *  @param sectionId Database column section_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class SectionSettingsRow(sectionId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching SectionSettingsRow objects using plain SQL queries */
  implicit def GetResultSectionSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[SectionSettingsRow] = GR{
    prs => SectionSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table section_settings. Objects of this class serve as prototypes for rows in queries. */
  class SectionSettings(tag: Tag) extends Table[SectionSettingsRow](tag, "section_settings") {
    def * = (sectionId, locale, settingName, settingValue, settingType) <> (SectionSettingsRow.tupled, SectionSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (sectionId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> SectionSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column section_id  */
    val sectionId: Column[Long] = column[Long]("section_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (sectionId,locale,settingName) (database name section_settings_pkey) */
    val index1 = index("section_settings_pkey", (sectionId, locale, settingName), unique=true)
    /** Index over (sectionId) (database name section_settings_section_id) */
    val index2 = index("section_settings_section_id", sectionId)
  }
  /** Collection-like TableQuery object for table SectionSettings */
  lazy val SectionSettings = new TableQuery(tag => new SectionSettings(tag))
  
  /** Entity class storing rows of table Sessions
   *  @param sessionId Database column session_id 
   *  @param userId Database column user_id 
   *  @param ipAddress Database column ip_address 
   *  @param userAgent Database column user_agent 
   *  @param created Database column created Default(0)
   *  @param lastUsed Database column last_used Default(0)
   *  @param remember Database column remember 
   *  @param data Database column data 
   *  @param actingAs Database column acting_as Default(0) */
  case class SessionsRow(sessionId: String, userId: Option[Long], ipAddress: String, userAgent: Option[String], created: Long = 0L, lastUsed: Long = 0L, remember: Byte, data: Option[String], actingAs: Long = 0L)
  /** GetResult implicit for fetching SessionsRow objects using plain SQL queries */
  implicit def GetResultSessionsRow(implicit e0: GR[String], e1: GR[Option[Long]], e2: GR[Option[String]], e3: GR[Long], e4: GR[Byte]): GR[SessionsRow] = GR{
    prs => SessionsRow.tupled((<<[String], <<?[Long], <<[String], <<?[String], <<[Long], <<[Long], <<[Byte], <<?[String], <<[Long]))
  }
  /** Table description of table sessions. Objects of this class serve as prototypes for rows in queries. */
  class Sessions(tag: Tag) extends Table[SessionsRow](tag, "sessions") {
    def * = (sessionId, userId, ipAddress, userAgent, created, lastUsed, remember, data, actingAs) <> (SessionsRow.tupled, SessionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (sessionId.?, userId, ipAddress.?, userAgent, created.?, lastUsed.?, remember.?, data, actingAs.?).shaped.<>({r=> _1.map(_=> SessionsRow.tupled((_1.get, _2, _3.get, _4, _5.get, _6.get, _7.get, _8, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column session_id  */
    val sessionId: Column[String] = column[String]("session_id")
    /** Database column user_id  */
    val userId: Column[Option[Long]] = column[Option[Long]]("user_id")
    /** Database column ip_address  */
    val ipAddress: Column[String] = column[String]("ip_address")
    /** Database column user_agent  */
    val userAgent: Column[Option[String]] = column[Option[String]]("user_agent")
    /** Database column created Default(0) */
    val created: Column[Long] = column[Long]("created", O.Default(0L))
    /** Database column last_used Default(0) */
    val lastUsed: Column[Long] = column[Long]("last_used", O.Default(0L))
    /** Database column remember  */
    val remember: Column[Byte] = column[Byte]("remember")
    /** Database column data  */
    val data: Column[Option[String]] = column[Option[String]]("data")
    /** Database column acting_as Default(0) */
    val actingAs: Column[Long] = column[Long]("acting_as", O.Default(0L))
    
    /** Uniqueness Index over (sessionId) (database name sessions_pkey) */
    val index1 = index("sessions_pkey", sessionId, unique=true)
    /** Index over (userId) (database name sessions_user_id) */
    val index2 = index("sessions_user_id", userId)
  }
  /** Collection-like TableQuery object for table Sessions */
  lazy val Sessions = new TableQuery(tag => new Sessions(tag))
  
  /** Entity class storing rows of table Signoffs
   *  @param signoffId Database column signoff_id AutoInc, PrimaryKey
   *  @param symbolic Database column symbolic 
   *  @param assocType Database column assoc_type Default(0)
   *  @param assocId Database column assoc_id Default(0)
   *  @param userId Database column user_id 
   *  @param fileId Database column file_id 
   *  @param fileRevision Database column file_revision 
   *  @param dateNotified Database column date_notified 
   *  @param dateUnderway Database column date_underway 
   *  @param dateCompleted Database column date_completed 
   *  @param dateAcknowledged Database column date_acknowledged 
   *  @param stageId Database column stage_id 
   *  @param userGroupId Database column user_group_id  */
  case class SignoffsRow(signoffId: Long, symbolic: String, assocType: Long = 0L, assocId: Long = 0L, userId: Long, fileId: Option[Long], fileRevision: Option[Long], dateNotified: Option[java.sql.Timestamp], dateUnderway: Option[java.sql.Timestamp], dateCompleted: Option[java.sql.Timestamp], dateAcknowledged: Option[java.sql.Timestamp], stageId: Option[Long], userGroupId: Option[Long])
  /** GetResult implicit for fetching SignoffsRow objects using plain SQL queries */
  implicit def GetResultSignoffsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]], e3: GR[Option[java.sql.Timestamp]]): GR[SignoffsRow] = GR{
    prs => SignoffsRow.tupled((<<[Long], <<[String], <<[Long], <<[Long], <<[Long], <<?[Long], <<?[Long], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<?[Long], <<?[Long]))
  }
  /** Table description of table signoffs. Objects of this class serve as prototypes for rows in queries. */
  class Signoffs(tag: Tag) extends Table[SignoffsRow](tag, "signoffs") {
    def * = (signoffId, symbolic, assocType, assocId, userId, fileId, fileRevision, dateNotified, dateUnderway, dateCompleted, dateAcknowledged, stageId, userGroupId) <> (SignoffsRow.tupled, SignoffsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (signoffId.?, symbolic.?, assocType.?, assocId.?, userId.?, fileId, fileRevision, dateNotified, dateUnderway, dateCompleted, dateAcknowledged, stageId, userGroupId).shaped.<>({r=> _1.map(_=> SignoffsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7, _8, _9, _10, _11, _12, _13)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column signoff_id AutoInc, PrimaryKey */
    val signoffId: Column[Long] = column[Long]("signoff_id", O.AutoInc, O.PrimaryKey)
    /** Database column symbolic  */
    val symbolic: Column[String] = column[String]("symbolic")
    /** Database column assoc_type Default(0) */
    val assocType: Column[Long] = column[Long]("assoc_type", O.Default(0L))
    /** Database column assoc_id Default(0) */
    val assocId: Column[Long] = column[Long]("assoc_id", O.Default(0L))
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column file_id  */
    val fileId: Column[Option[Long]] = column[Option[Long]]("file_id")
    /** Database column file_revision  */
    val fileRevision: Column[Option[Long]] = column[Option[Long]]("file_revision")
    /** Database column date_notified  */
    val dateNotified: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_notified")
    /** Database column date_underway  */
    val dateUnderway: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_underway")
    /** Database column date_completed  */
    val dateCompleted: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_completed")
    /** Database column date_acknowledged  */
    val dateAcknowledged: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_acknowledged")
    /** Database column stage_id  */
    val stageId: Column[Option[Long]] = column[Option[Long]]("stage_id")
    /** Database column user_group_id  */
    val userGroupId: Column[Option[Long]] = column[Option[Long]]("user_group_id")
    
    /** Uniqueness Index over (assocType,assocId,symbolic,userId,stageId,userGroupId) (database name signoff_symbolic) */
    val index1 = index("signoff_symbolic", (assocType, assocId, symbolic, userId, stageId, userGroupId), unique=true)
  }
  /** Collection-like TableQuery object for table Signoffs */
  lazy val Signoffs = new TableQuery(tag => new Signoffs(tag))
  
  /** Entity class storing rows of table Site
   *  @param redirect Database column redirect Default(0)
   *  @param primaryLocale Database column primary_locale 
   *  @param minPasswordLength Database column min_password_length 
   *  @param installedLocales Database column installed_locales 
   *  @param supportedLocales Database column supported_locales 
   *  @param originalStyleFileName Database column original_style_file_name  */
  case class SiteRow(redirect: Long = 0L, primaryLocale: String, minPasswordLength: Byte, installedLocales: String, supportedLocales: Option[String], originalStyleFileName: Option[String])
  /** GetResult implicit for fetching SiteRow objects using plain SQL queries */
  implicit def GetResultSiteRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Byte], e3: GR[Option[String]]): GR[SiteRow] = GR{
    prs => SiteRow.tupled((<<[Long], <<[String], <<[Byte], <<[String], <<?[String], <<?[String]))
  }
  /** Table description of table site. Objects of this class serve as prototypes for rows in queries. */
  class Site(tag: Tag) extends Table[SiteRow](tag, "site") {
    def * = (redirect, primaryLocale, minPasswordLength, installedLocales, supportedLocales, originalStyleFileName) <> (SiteRow.tupled, SiteRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (redirect.?, primaryLocale.?, minPasswordLength.?, installedLocales.?, supportedLocales, originalStyleFileName).shaped.<>({r=> _1.map(_=> SiteRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column redirect Default(0) */
    val redirect: Column[Long] = column[Long]("redirect", O.Default(0L))
    /** Database column primary_locale  */
    val primaryLocale: Column[String] = column[String]("primary_locale")
    /** Database column min_password_length  */
    val minPasswordLength: Column[Byte] = column[Byte]("min_password_length")
    /** Database column installed_locales  */
    val installedLocales: Column[String] = column[String]("installed_locales")
    /** Database column supported_locales  */
    val supportedLocales: Column[Option[String]] = column[Option[String]]("supported_locales")
    /** Database column original_style_file_name  */
    val originalStyleFileName: Column[Option[String]] = column[Option[String]]("original_style_file_name")
  }
  /** Collection-like TableQuery object for table Site */
  lazy val Site = new TableQuery(tag => new Site(tag))
  
  /** Entity class storing rows of table SiteSettings
   *  @param settingName Database column setting_name 
   *  @param locale Database column locale 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class SiteSettingsRow(settingName: String, locale: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching SiteSettingsRow objects using plain SQL queries */
  implicit def GetResultSiteSettingsRow(implicit e0: GR[String], e1: GR[Option[String]]): GR[SiteSettingsRow] = GR{
    prs => SiteSettingsRow.tupled((<<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table site_settings. Objects of this class serve as prototypes for rows in queries. */
  class SiteSettings(tag: Tag) extends Table[SiteSettingsRow](tag, "site_settings") {
    def * = (settingName, locale, settingValue, settingType) <> (SiteSettingsRow.tupled, SiteSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (settingName.?, locale.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> SiteSettingsRow.tupled((_1.get, _2.get, _3, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (settingName,locale) (database name site_settings_pkey) */
    val index1 = index("site_settings_pkey", (settingName, locale), unique=true)
  }
  /** Collection-like TableQuery object for table SiteSettings */
  lazy val SiteSettings = new TableQuery(tag => new SiteSettings(tag))
  
  /** Entity class storing rows of table StaticPages
   *  @param staticPageId Database column static_page_id AutoInc, PrimaryKey
   *  @param path Database column path 
   *  @param journalId Database column journal_id  */
  case class StaticPagesRow(staticPageId: Long, path: String, journalId: Long)
  /** GetResult implicit for fetching StaticPagesRow objects using plain SQL queries */
  implicit def GetResultStaticPagesRow(implicit e0: GR[Long], e1: GR[String]): GR[StaticPagesRow] = GR{
    prs => StaticPagesRow.tupled((<<[Long], <<[String], <<[Long]))
  }
  /** Table description of table static_pages. Objects of this class serve as prototypes for rows in queries. */
  class StaticPages(tag: Tag) extends Table[StaticPagesRow](tag, "static_pages") {
    def * = (staticPageId, path, journalId) <> (StaticPagesRow.tupled, StaticPagesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (staticPageId.?, path.?, journalId.?).shaped.<>({r=> _1.map(_=> StaticPagesRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column static_page_id AutoInc, PrimaryKey */
    val staticPageId: Column[Long] = column[Long]("static_page_id", O.AutoInc, O.PrimaryKey)
    /** Database column path  */
    val path: Column[String] = column[String]("path")
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
  }
  /** Collection-like TableQuery object for table StaticPages */
  lazy val StaticPages = new TableQuery(tag => new StaticPages(tag))
  
  /** Entity class storing rows of table StaticPageSettings
   *  @param staticPageId Database column static_page_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class StaticPageSettingsRow(staticPageId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching StaticPageSettingsRow objects using plain SQL queries */
  implicit def GetResultStaticPageSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[StaticPageSettingsRow] = GR{
    prs => StaticPageSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table static_page_settings. Objects of this class serve as prototypes for rows in queries. */
  class StaticPageSettings(tag: Tag) extends Table[StaticPageSettingsRow](tag, "static_page_settings") {
    def * = (staticPageId, locale, settingName, settingValue, settingType) <> (StaticPageSettingsRow.tupled, StaticPageSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (staticPageId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> StaticPageSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column static_page_id  */
    val staticPageId: Column[Long] = column[Long]("static_page_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (staticPageId,locale,settingName) (database name static_page_settings_pkey) */
    val index1 = index("static_page_settings_pkey", (staticPageId, locale, settingName), unique=true)
    /** Index over (staticPageId) (database name static_page_settings_static_page_id) */
    val index2 = index("static_page_settings_static_page_id", staticPageId)
  }
  /** Collection-like TableQuery object for table StaticPageSettings */
  lazy val StaticPageSettings = new TableQuery(tag => new StaticPageSettings(tag))
  
  /** Entity class storing rows of table Subscriptions
   *  @param subscriptionId Database column subscription_id AutoInc, PrimaryKey
   *  @param journalId Database column journal_id 
   *  @param userId Database column user_id 
   *  @param typeId Database column type_id 
   *  @param dateStart Database column date_start 
   *  @param dateEnd Database column date_end 
   *  @param status Database column status 
   *  @param membership Database column membership 
   *  @param referenceNumber Database column reference_number 
   *  @param notes Database column notes  */
  case class SubscriptionsRow(subscriptionId: Long, journalId: Long, userId: Long, typeId: Long, dateStart: Option[java.sql.Date], dateEnd: Option[java.sql.Timestamp], status: Byte, membership: Option[String], referenceNumber: Option[String], notes: Option[String])
  /** GetResult implicit for fetching SubscriptionsRow objects using plain SQL queries */
  implicit def GetResultSubscriptionsRow(implicit e0: GR[Long], e1: GR[Option[java.sql.Date]], e2: GR[Option[java.sql.Timestamp]], e3: GR[Byte], e4: GR[Option[String]]): GR[SubscriptionsRow] = GR{
    prs => SubscriptionsRow.tupled((<<[Long], <<[Long], <<[Long], <<[Long], <<?[java.sql.Date], <<?[java.sql.Timestamp], <<[Byte], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table subscriptions. Objects of this class serve as prototypes for rows in queries. */
  class Subscriptions(tag: Tag) extends Table[SubscriptionsRow](tag, "subscriptions") {
    def * = (subscriptionId, journalId, userId, typeId, dateStart, dateEnd, status, membership, referenceNumber, notes) <> (SubscriptionsRow.tupled, SubscriptionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (subscriptionId.?, journalId.?, userId.?, typeId.?, dateStart, dateEnd, status.?, membership, referenceNumber, notes).shaped.<>({r=> _1.map(_=> SubscriptionsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7.get, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column subscription_id AutoInc, PrimaryKey */
    val subscriptionId: Column[Long] = column[Long]("subscription_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column type_id  */
    val typeId: Column[Long] = column[Long]("type_id")
    /** Database column date_start  */
    val dateStart: Column[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date_start")
    /** Database column date_end  */
    val dateEnd: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_end")
    /** Database column status  */
    val status: Column[Byte] = column[Byte]("status")
    /** Database column membership  */
    val membership: Column[Option[String]] = column[Option[String]]("membership")
    /** Database column reference_number  */
    val referenceNumber: Column[Option[String]] = column[Option[String]]("reference_number")
    /** Database column notes  */
    val notes: Column[Option[String]] = column[Option[String]]("notes")
  }
  /** Collection-like TableQuery object for table Subscriptions */
  lazy val Subscriptions = new TableQuery(tag => new Subscriptions(tag))
  
  /** Entity class storing rows of table SubscriptionTypes
   *  @param typeId Database column type_id AutoInc, PrimaryKey
   *  @param journalId Database column journal_id 
   *  @param cost Database column cost 
   *  @param currencyCodeAlpha Database column currency_code_alpha 
   *  @param nonExpiring Database column non_expiring 
   *  @param duration Database column duration 
   *  @param format Database column format 
   *  @param institutional Database column institutional 
   *  @param membership Database column membership 
   *  @param disablePublicDisplay Database column disable_public_display 
   *  @param seq Database column seq  */
  case class SubscriptionTypesRow(typeId: Long, journalId: Long, cost: Double, currencyCodeAlpha: String, nonExpiring: Byte, duration: Option[Short], format: Short, institutional: Byte, membership: Byte, disablePublicDisplay: Byte, seq: Double)
  /** GetResult implicit for fetching SubscriptionTypesRow objects using plain SQL queries */
  implicit def GetResultSubscriptionTypesRow(implicit e0: GR[Long], e1: GR[Double], e2: GR[String], e3: GR[Byte], e4: GR[Option[Short]], e5: GR[Short]): GR[SubscriptionTypesRow] = GR{
    prs => SubscriptionTypesRow.tupled((<<[Long], <<[Long], <<[Double], <<[String], <<[Byte], <<?[Short], <<[Short], <<[Byte], <<[Byte], <<[Byte], <<[Double]))
  }
  /** Table description of table subscription_types. Objects of this class serve as prototypes for rows in queries. */
  class SubscriptionTypes(tag: Tag) extends Table[SubscriptionTypesRow](tag, "subscription_types") {
    def * = (typeId, journalId, cost, currencyCodeAlpha, nonExpiring, duration, format, institutional, membership, disablePublicDisplay, seq) <> (SubscriptionTypesRow.tupled, SubscriptionTypesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (typeId.?, journalId.?, cost.?, currencyCodeAlpha.?, nonExpiring.?, duration, format.?, institutional.?, membership.?, disablePublicDisplay.?, seq.?).shaped.<>({r=> _1.map(_=> SubscriptionTypesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6, _7.get, _8.get, _9.get, _10.get, _11.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column type_id AutoInc, PrimaryKey */
    val typeId: Column[Long] = column[Long]("type_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column cost  */
    val cost: Column[Double] = column[Double]("cost")
    /** Database column currency_code_alpha  */
    val currencyCodeAlpha: Column[String] = column[String]("currency_code_alpha")
    /** Database column non_expiring  */
    val nonExpiring: Column[Byte] = column[Byte]("non_expiring")
    /** Database column duration  */
    val duration: Column[Option[Short]] = column[Option[Short]]("duration")
    /** Database column format  */
    val format: Column[Short] = column[Short]("format")
    /** Database column institutional  */
    val institutional: Column[Byte] = column[Byte]("institutional")
    /** Database column membership  */
    val membership: Column[Byte] = column[Byte]("membership")
    /** Database column disable_public_display  */
    val disablePublicDisplay: Column[Byte] = column[Byte]("disable_public_display")
    /** Database column seq  */
    val seq: Column[Double] = column[Double]("seq")
  }
  /** Collection-like TableQuery object for table SubscriptionTypes */
  lazy val SubscriptionTypes = new TableQuery(tag => new SubscriptionTypes(tag))
  
  /** Entity class storing rows of table SubscriptionTypeSettings
   *  @param typeId Database column type_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class SubscriptionTypeSettingsRow(typeId: Long, locale: String, settingName: String, settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching SubscriptionTypeSettingsRow objects using plain SQL queries */
  implicit def GetResultSubscriptionTypeSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[SubscriptionTypeSettingsRow] = GR{
    prs => SubscriptionTypeSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table subscription_type_settings. Objects of this class serve as prototypes for rows in queries. */
  class SubscriptionTypeSettings(tag: Tag) extends Table[SubscriptionTypeSettingsRow](tag, "subscription_type_settings") {
    def * = (typeId, locale, settingName, settingValue, settingType) <> (SubscriptionTypeSettingsRow.tupled, SubscriptionTypeSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (typeId.?, locale.?, settingName.?, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> SubscriptionTypeSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column type_id  */
    val typeId: Column[Long] = column[Long]("type_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (typeId,locale,settingName) (database name subscription_type_settings_pkey) */
    val index1 = index("subscription_type_settings_pkey", (typeId, locale, settingName), unique=true)
    /** Index over (typeId) (database name subscription_type_settings_type_id) */
    val index2 = index("subscription_type_settings_type_id", typeId)
  }
  /** Collection-like TableQuery object for table SubscriptionTypeSettings */
  lazy val SubscriptionTypeSettings = new TableQuery(tag => new SubscriptionTypeSettings(tag))
  
  /** Entity class storing rows of table TemporaryFiles
   *  @param fileId Database column file_id AutoInc, PrimaryKey
   *  @param userId Database column user_id 
   *  @param fileName Database column file_name 
   *  @param fileType Database column file_type 
   *  @param fileSize Database column file_size 
   *  @param originalFileName Database column original_file_name 
   *  @param dateUploaded Database column date_uploaded  */
  case class TemporaryFilesRow(fileId: Long, userId: Long, fileName: String, fileType: Option[String], fileSize: Long, originalFileName: Option[String], dateUploaded: java.sql.Timestamp)
  /** GetResult implicit for fetching TemporaryFilesRow objects using plain SQL queries */
  implicit def GetResultTemporaryFilesRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[java.sql.Timestamp]): GR[TemporaryFilesRow] = GR{
    prs => TemporaryFilesRow.tupled((<<[Long], <<[Long], <<[String], <<?[String], <<[Long], <<?[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table temporary_files. Objects of this class serve as prototypes for rows in queries. */
  class TemporaryFiles(tag: Tag) extends Table[TemporaryFilesRow](tag, "temporary_files") {
    def * = (fileId, userId, fileName, fileType, fileSize, originalFileName, dateUploaded) <> (TemporaryFilesRow.tupled, TemporaryFilesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (fileId.?, userId.?, fileName.?, fileType, fileSize.?, originalFileName, dateUploaded.?).shaped.<>({r=> _1.map(_=> TemporaryFilesRow.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column file_id AutoInc, PrimaryKey */
    val fileId: Column[Long] = column[Long]("file_id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column file_name  */
    val fileName: Column[String] = column[String]("file_name")
    /** Database column file_type  */
    val fileType: Column[Option[String]] = column[Option[String]]("file_type")
    /** Database column file_size  */
    val fileSize: Column[Long] = column[Long]("file_size")
    /** Database column original_file_name  */
    val originalFileName: Column[Option[String]] = column[Option[String]]("original_file_name")
    /** Database column date_uploaded  */
    val dateUploaded: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_uploaded")
    
    /** Index over (userId) (database name temporary_files_user_id) */
    val index1 = index("temporary_files_user_id", userId)
  }
  /** Collection-like TableQuery object for table TemporaryFiles */
  lazy val TemporaryFiles = new TableQuery(tag => new TemporaryFiles(tag))
  
  /** Row type of table Theses */
  type ThesesRow = HCons[Long,HCons[Long,HCons[Short,HCons[Short,HCons[Option[String],HCons[String,HCons[String,HCons[java.sql.Timestamp,HCons[String,HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[String,HCons[Option[String],HCons[String,HCons[String,HCons[Option[Byte],HCons[Option[String],HCons[String,HCons[Option[String],HCons[String,HCons[String,HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[java.sql.Timestamp,HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for ThesesRow providing default values if available in the database schema. */
  def ThesesRow(thesisId: Long, journalId: Long, status: Short, degree: Short, degreeName: Option[String], department: String, university: String, dateApproved: java.sql.Timestamp, title: String, url: Option[String], `abstract`: Option[String], comment: Option[String], studentFirstName: String, studentMiddleName: Option[String], studentLastName: String, studentEmail: String, studentEmailPublish: Option[Byte] = None, studentBio: Option[String], supervisorFirstName: String, supervisorMiddleName: Option[String], supervisorLastName: String, supervisorEmail: String, discipline: Option[String], subjectClass: Option[String], subject: Option[String], coverageGeo: Option[String], coverageChron: Option[String], coverageSample: Option[String], method: Option[String], language: Option[String] = None, dateSubmitted: java.sql.Timestamp): ThesesRow = {
    thesisId :: journalId :: status :: degree :: degreeName :: department :: university :: dateApproved :: title :: url :: `abstract` :: comment :: studentFirstName :: studentMiddleName :: studentLastName :: studentEmail :: studentEmailPublish :: studentBio :: supervisorFirstName :: supervisorMiddleName :: supervisorLastName :: supervisorEmail :: discipline :: subjectClass :: subject :: coverageGeo :: coverageChron :: coverageSample :: method :: language :: dateSubmitted :: HNil
  }
  /** GetResult implicit for fetching ThesesRow objects using plain SQL queries */
  implicit def GetResultThesesRow(implicit e0: GR[Long], e1: GR[Short], e2: GR[Option[String]], e3: GR[String], e4: GR[java.sql.Timestamp], e5: GR[Option[Byte]]): GR[ThesesRow] = GR{
    prs => <<[Long] :: <<[Long] :: <<[Short] :: <<[Short] :: <<?[String] :: <<[String] :: <<[String] :: <<[java.sql.Timestamp] :: <<[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<[String] :: <<?[String] :: <<[String] :: <<[String] :: <<?[Byte] :: <<?[String] :: <<[String] :: <<?[String] :: <<[String] :: <<[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<[java.sql.Timestamp] :: HNil
  }
  /** Table description of table theses. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: abstract */
  class Theses(tag: Tag) extends Table[ThesesRow](tag, "theses") {
    def * = thesisId :: journalId :: status :: degree :: degreeName :: department :: university :: dateApproved :: title :: url :: `abstract` :: comment :: studentFirstName :: studentMiddleName :: studentLastName :: studentEmail :: studentEmailPublish :: studentBio :: supervisorFirstName :: supervisorMiddleName :: supervisorLastName :: supervisorEmail :: discipline :: subjectClass :: subject :: coverageGeo :: coverageChron :: coverageSample :: method :: language :: dateSubmitted :: HNil
    
    /** Database column thesis_id AutoInc, PrimaryKey */
    val thesisId: Column[Long] = column[Long]("thesis_id", O.AutoInc, O.PrimaryKey)
    /** Database column journal_id  */
    val journalId: Column[Long] = column[Long]("journal_id")
    /** Database column status  */
    val status: Column[Short] = column[Short]("status")
    /** Database column degree  */
    val degree: Column[Short] = column[Short]("degree")
    /** Database column degree_name  */
    val degreeName: Column[Option[String]] = column[Option[String]]("degree_name")
    /** Database column department  */
    val department: Column[String] = column[String]("department")
    /** Database column university  */
    val university: Column[String] = column[String]("university")
    /** Database column date_approved  */
    val dateApproved: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_approved")
    /** Database column title  */
    val title: Column[String] = column[String]("title")
    /** Database column url  */
    val url: Column[Option[String]] = column[Option[String]]("url")
    /** Database column abstract 
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `abstract`: Column[Option[String]] = column[Option[String]]("abstract")
    /** Database column comment  */
    val comment: Column[Option[String]] = column[Option[String]]("comment")
    /** Database column student_first_name  */
    val studentFirstName: Column[String] = column[String]("student_first_name")
    /** Database column student_middle_name  */
    val studentMiddleName: Column[Option[String]] = column[Option[String]]("student_middle_name")
    /** Database column student_last_name  */
    val studentLastName: Column[String] = column[String]("student_last_name")
    /** Database column student_email  */
    val studentEmail: Column[String] = column[String]("student_email")
    /** Database column student_email_publish Default(None) */
    val studentEmailPublish: Column[Option[Byte]] = column[Option[Byte]]("student_email_publish", O.Default(None))
    /** Database column student_bio  */
    val studentBio: Column[Option[String]] = column[Option[String]]("student_bio")
    /** Database column supervisor_first_name  */
    val supervisorFirstName: Column[String] = column[String]("supervisor_first_name")
    /** Database column supervisor_middle_name  */
    val supervisorMiddleName: Column[Option[String]] = column[Option[String]]("supervisor_middle_name")
    /** Database column supervisor_last_name  */
    val supervisorLastName: Column[String] = column[String]("supervisor_last_name")
    /** Database column supervisor_email  */
    val supervisorEmail: Column[String] = column[String]("supervisor_email")
    /** Database column discipline  */
    val discipline: Column[Option[String]] = column[Option[String]]("discipline")
    /** Database column subject_class  */
    val subjectClass: Column[Option[String]] = column[Option[String]]("subject_class")
    /** Database column subject  */
    val subject: Column[Option[String]] = column[Option[String]]("subject")
    /** Database column coverage_geo  */
    val coverageGeo: Column[Option[String]] = column[Option[String]]("coverage_geo")
    /** Database column coverage_chron  */
    val coverageChron: Column[Option[String]] = column[Option[String]]("coverage_chron")
    /** Database column coverage_sample  */
    val coverageSample: Column[Option[String]] = column[Option[String]]("coverage_sample")
    /** Database column method  */
    val method: Column[Option[String]] = column[Option[String]]("method")
    /** Database column language Default(None) */
    val language: Column[Option[String]] = column[Option[String]]("language", O.Default(None))
    /** Database column date_submitted  */
    val dateSubmitted: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_submitted")
    
    /** Index over (journalId) (database name theses_journal_id) */
    val index1 = index("theses_journal_id", journalId :: HNil)
  }
  /** Collection-like TableQuery object for table Theses */
  lazy val Theses = new TableQuery(tag => new Theses(tag))
  
  /** Entity class storing rows of table UserInterests
   *  @param userId Database column user_id 
   *  @param controlledVocabEntryId Database column controlled_vocab_entry_id  */
  case class UserInterestsRow(userId: Long, controlledVocabEntryId: Long)
  /** GetResult implicit for fetching UserInterestsRow objects using plain SQL queries */
  implicit def GetResultUserInterestsRow(implicit e0: GR[Long]): GR[UserInterestsRow] = GR{
    prs => UserInterestsRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table user_interests. Objects of this class serve as prototypes for rows in queries. */
  class UserInterests(tag: Tag) extends Table[UserInterestsRow](tag, "user_interests") {
    def * = (userId, controlledVocabEntryId) <> (UserInterestsRow.tupled, UserInterestsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (userId.?, controlledVocabEntryId.?).shaped.<>({r=> _1.map(_=> UserInterestsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column controlled_vocab_entry_id  */
    val controlledVocabEntryId: Column[Long] = column[Long]("controlled_vocab_entry_id")
    
    /** Uniqueness Index over (userId,controlledVocabEntryId) (database name u_e_pkey) */
    val index1 = index("u_e_pkey", (userId, controlledVocabEntryId), unique=true)
  }
  /** Collection-like TableQuery object for table UserInterests */
  lazy val UserInterests = new TableQuery(tag => new UserInterests(tag))
  
  /** Row type of table Users */
  type UsersRow = HCons[Long,HCons[String,HCons[String,HCons[Option[String],HCons[String,HCons[Option[String],HCons[String,HCons[Option[String],HCons[Option[String],HCons[String,HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[String],HCons[Option[java.sql.Timestamp],HCons[java.sql.Timestamp,HCons[Option[java.sql.Timestamp],HCons[java.sql.Timestamp,HCons[Option[Byte],HCons[Option[Long],HCons[Option[String],HCons[Byte,HCons[Option[String],HNil]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for UsersRow providing default values if available in the database schema. */
  def UsersRow(userId: Long, username: String, password: String, salutation: Option[String], firstName: String, middleName: Option[String], lastName: String, gender: Option[String], initials: Option[String], email: String, url: Option[String], phone: Option[String], fax: Option[String], mailingAddress: Option[String], country: Option[String], locales: Option[String], dateLastEmail: Option[java.sql.Timestamp], dateRegistered: java.sql.Timestamp, dateValidated: Option[java.sql.Timestamp], dateLastLogin: java.sql.Timestamp, mustChangePassword: Option[Byte], authId: Option[Long], authStr: Option[String], disabled: Byte, disabledReason: Option[String]): UsersRow = {
    userId :: username :: password :: salutation :: firstName :: middleName :: lastName :: gender :: initials :: email :: url :: phone :: fax :: mailingAddress :: country :: locales :: dateLastEmail :: dateRegistered :: dateValidated :: dateLastLogin :: mustChangePassword :: authId :: authStr :: disabled :: disabledReason :: HNil
  }
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]], e4: GR[java.sql.Timestamp], e5: GR[Option[Byte]], e6: GR[Option[Long]], e7: GR[Byte]): GR[UsersRow] = GR{
    prs => <<[Long] :: <<[String] :: <<[String] :: <<?[String] :: <<[String] :: <<?[String] :: <<[String] :: <<?[String] :: <<?[String] :: <<[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[String] :: <<?[java.sql.Timestamp] :: <<[java.sql.Timestamp] :: <<?[java.sql.Timestamp] :: <<[java.sql.Timestamp] :: <<?[Byte] :: <<?[Long] :: <<?[String] :: <<[Byte] :: <<?[String] :: HNil
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(tag: Tag) extends Table[UsersRow](tag, "users") {
    def * = userId :: username :: password :: salutation :: firstName :: middleName :: lastName :: gender :: initials :: email :: url :: phone :: fax :: mailingAddress :: country :: locales :: dateLastEmail :: dateRegistered :: dateValidated :: dateLastLogin :: mustChangePassword :: authId :: authStr :: disabled :: disabledReason :: HNil
    
    /** Database column user_id AutoInc, PrimaryKey */
    val userId: Column[Long] = column[Long]("user_id", O.AutoInc, O.PrimaryKey)
    /** Database column username  */
    val username: Column[String] = column[String]("username")
    /** Database column password  */
    val password: Column[String] = column[String]("password")
    /** Database column salutation  */
    val salutation: Column[Option[String]] = column[Option[String]]("salutation")
    /** Database column first_name  */
    val firstName: Column[String] = column[String]("first_name")
    /** Database column middle_name  */
    val middleName: Column[Option[String]] = column[Option[String]]("middle_name")
    /** Database column last_name  */
    val lastName: Column[String] = column[String]("last_name")
    /** Database column gender  */
    val gender: Column[Option[String]] = column[Option[String]]("gender")
    /** Database column initials  */
    val initials: Column[Option[String]] = column[Option[String]]("initials")
    /** Database column email  */
    val email: Column[String] = column[String]("email")
    /** Database column url  */
    val url: Column[Option[String]] = column[Option[String]]("url")
    /** Database column phone  */
    val phone: Column[Option[String]] = column[Option[String]]("phone")
    /** Database column fax  */
    val fax: Column[Option[String]] = column[Option[String]]("fax")
    /** Database column mailing_address  */
    val mailingAddress: Column[Option[String]] = column[Option[String]]("mailing_address")
    /** Database column country  */
    val country: Column[Option[String]] = column[Option[String]]("country")
    /** Database column locales  */
    val locales: Column[Option[String]] = column[Option[String]]("locales")
    /** Database column date_last_email  */
    val dateLastEmail: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_last_email")
    /** Database column date_registered  */
    val dateRegistered: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_registered")
    /** Database column date_validated  */
    val dateValidated: Column[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("date_validated")
    /** Database column date_last_login  */
    val dateLastLogin: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_last_login")
    /** Database column must_change_password  */
    val mustChangePassword: Column[Option[Byte]] = column[Option[Byte]]("must_change_password")
    /** Database column auth_id  */
    val authId: Column[Option[Long]] = column[Option[Long]]("auth_id")
    /** Database column auth_str  */
    val authStr: Column[Option[String]] = column[Option[String]]("auth_str")
    /** Database column disabled  */
    val disabled: Column[Byte] = column[Byte]("disabled")
    /** Database column disabled_reason  */
    val disabledReason: Column[Option[String]] = column[Option[String]]("disabled_reason")
    
    /** Uniqueness Index over (email) (database name users_email) */
    val index1 = index("users_email", email :: HNil, unique=true)
    /** Uniqueness Index over (username) (database name users_username) */
    val index2 = index("users_username", username :: HNil, unique=true)
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
  
  /** Entity class storing rows of table UserSettings
   *  @param userId Database column user_id 
   *  @param locale Database column locale 
   *  @param settingName Database column setting_name 
   *  @param assocType Database column assoc_type Default(Some(0))
   *  @param assocId Database column assoc_id Default(Some(0))
   *  @param settingValue Database column setting_value 
   *  @param settingType Database column setting_type  */
  case class UserSettingsRow(userId: Long, locale: String, settingName: String, assocType: Option[Long] = Some(0L), assocId: Option[Long] = Some(0L), settingValue: Option[String], settingType: String)
  /** GetResult implicit for fetching UserSettingsRow objects using plain SQL queries */
  implicit def GetResultUserSettingsRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[Long]], e3: GR[Option[String]]): GR[UserSettingsRow] = GR{
    prs => UserSettingsRow.tupled((<<[Long], <<[String], <<[String], <<?[Long], <<?[Long], <<?[String], <<[String]))
  }
  /** Table description of table user_settings. Objects of this class serve as prototypes for rows in queries. */
  class UserSettings(tag: Tag) extends Table[UserSettingsRow](tag, "user_settings") {
    def * = (userId, locale, settingName, assocType, assocId, settingValue, settingType) <> (UserSettingsRow.tupled, UserSettingsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (userId.?, locale.?, settingName.?, assocType, assocId, settingValue, settingType.?).shaped.<>({r=> _1.map(_=> UserSettingsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column user_id  */
    val userId: Column[Long] = column[Long]("user_id")
    /** Database column locale  */
    val locale: Column[String] = column[String]("locale")
    /** Database column setting_name  */
    val settingName: Column[String] = column[String]("setting_name")
    /** Database column assoc_type Default(Some(0)) */
    val assocType: Column[Option[Long]] = column[Option[Long]]("assoc_type", O.Default(Some(0L)))
    /** Database column assoc_id Default(Some(0)) */
    val assocId: Column[Option[Long]] = column[Option[Long]]("assoc_id", O.Default(Some(0L)))
    /** Database column setting_value  */
    val settingValue: Column[Option[String]] = column[Option[String]]("setting_value")
    /** Database column setting_type  */
    val settingType: Column[String] = column[String]("setting_type")
    
    /** Uniqueness Index over (userId,locale,settingName,assocType,assocId) (database name user_settings_pkey) */
    val index1 = index("user_settings_pkey", (userId, locale, settingName, assocType, assocId), unique=true)
    /** Index over (userId) (database name user_settings_user_id) */
    val index2 = index("user_settings_user_id", userId)
  }
  /** Collection-like TableQuery object for table UserSettings */
  lazy val UserSettings = new TableQuery(tag => new UserSettings(tag))
  
  /** Entity class storing rows of table Versions
   *  @param major Database column major Default(0)
   *  @param minor Database column minor Default(0)
   *  @param revision Database column revision Default(0)
   *  @param build Database column build Default(0)
   *  @param dateInstalled Database column date_installed 
   *  @param current Database column current 
   *  @param productType Database column product_type 
   *  @param product Database column product 
   *  @param productClassName Database column product_class_name 
   *  @param lazyLoad Database column lazy_load 
   *  @param sitewide Database column sitewide  */
  case class VersionsRow(major: Int = 0, minor: Int = 0, revision: Int = 0, build: Int = 0, dateInstalled: java.sql.Timestamp, current: Byte, productType: Option[String], product: Option[String], productClassName: Option[String], lazyLoad: Byte, sitewide: Byte)
  /** GetResult implicit for fetching VersionsRow objects using plain SQL queries */
  implicit def GetResultVersionsRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[Byte], e3: GR[Option[String]]): GR[VersionsRow] = GR{
    prs => VersionsRow.tupled((<<[Int], <<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[Byte], <<?[String], <<?[String], <<?[String], <<[Byte], <<[Byte]))
  }
  /** Table description of table versions. Objects of this class serve as prototypes for rows in queries. */
  class Versions(tag: Tag) extends Table[VersionsRow](tag, "versions") {
    def * = (major, minor, revision, build, dateInstalled, current, productType, product, productClassName, lazyLoad, sitewide) <> (VersionsRow.tupled, VersionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (major.?, minor.?, revision.?, build.?, dateInstalled.?, current.?, productType, product, productClassName, lazyLoad.?, sitewide.?).shaped.<>({r=> _1.map(_=> VersionsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8, _9, _10.get, _11.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column major Default(0) */
    val major: Column[Int] = column[Int]("major", O.Default(0))
    /** Database column minor Default(0) */
    val minor: Column[Int] = column[Int]("minor", O.Default(0))
    /** Database column revision Default(0) */
    val revision: Column[Int] = column[Int]("revision", O.Default(0))
    /** Database column build Default(0) */
    val build: Column[Int] = column[Int]("build", O.Default(0))
    /** Database column date_installed  */
    val dateInstalled: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("date_installed")
    /** Database column current  */
    val current: Column[Byte] = column[Byte]("current")
    /** Database column product_type  */
    val productType: Column[Option[String]] = column[Option[String]]("product_type")
    /** Database column product  */
    val product: Column[Option[String]] = column[Option[String]]("product")
    /** Database column product_class_name  */
    val productClassName: Column[Option[String]] = column[Option[String]]("product_class_name")
    /** Database column lazy_load  */
    val lazyLoad: Column[Byte] = column[Byte]("lazy_load")
    /** Database column sitewide  */
    val sitewide: Column[Byte] = column[Byte]("sitewide")
    
    /** Uniqueness Index over (product,major,minor,revision,build) (database name versions_pkey) */
    val index1 = index("versions_pkey", (product, major, minor, revision, build), unique=true)
  }
  /** Collection-like TableQuery object for table Versions */
  lazy val Versions = new TableQuery(tag => new Versions(tag))
}