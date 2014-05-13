package models.copyright

object CopyrightTransferStatus extends Enumeration {
    type Status = Value
    val UNCONFIRMED = Value("Unconfirmed")
    val CONFIRMED = Value("Confirmed")
}
