package models

/**
 * Created by Zeuko on 05.04.14.
 */
case class CopyrightData(
                          id: Int,
                          title: String,
                          correspondingAuthor: Author,
                          contribution: List[AuthorContribution],
                          financialDisclosure: String
                          ) {
}
