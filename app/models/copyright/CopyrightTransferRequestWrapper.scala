package models.copyright

case class CopyrightTransferRequestWrapper(copyrightTransferRequest: CopyrightTransferRequest,
                                           copyright: Copyright,
                                           correspondingAuthor: CorrespondingAuthor,
                                           contributionList: List[Contribution]) {

}
