package models

/**
 * Created by Zeuko on 05.04.14.
 */
case class Copyright(
                          title: String,
                          correspondingAuthor: String,
                          contribution: List[Contribution],
                          financialDisclosure: String
                          ) {

  def getAsFormattedString(): String = {
    return "Paper title: "+ title +
      "\n\rCorresponding author: "+  correspondingAuthor +
      "\n\rContribution to paper: "+  contribution +
      "\n\rFinancial disclosure: "+  financialDisclosure;
  }
}
