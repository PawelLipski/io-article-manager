package models

/**
 * Created by Zeuko on 05.04.14.
 */
case class CopyrightData(
                          title: String,
                          authors: String,
                          correspondingAuthor: String,
                          contribution: String,
                          financialDisclosure: String
                          ) {

  def getAsFormattedString(): String = {
    return "Paper title: "+ title +
      "\n\rAuthors: "+ authors +
      "\n\rCorresponding author: "+  correspondingAuthor +
      "\n\rContribution to paper: "+  contribution +
      "\n\rFinancial disclosure: "+  financialDisclosure;
  }
}
