package models.copyright

/**
 * Created by Zeuko on 05.04.14.
 */
case class Copyright(
                          title: String,
                          correspondingAuthor: CorrespondingAuthor,
                          contribution: List[Contribution],
                          financialDisclosure: String
                          ) {

  def getAsFormattedString(): String = {
    return "Paper title: "+ title +
      "\n\rCorresponding author: \n\r"+  correspondingAuthor.toString +
      "\n\rContribution to paper: \n\r"+  getContribution() +
      "\n\rFinancial disclosure: \n\r"+  financialDisclosure;
  }

  private def getContribution(): String = {
    var string = ""
    for (c <- contribution) {
      string += "Author: "
      string += c.toString
      string += "\n\r"
    }
    return string
  }


}
