
# ?zeroDateTimeBehavior=convertToNull is needed to delete a row with null date
export OJS_DB_URL="jdbc:mysql://sql.udl.pl:3306/slonka_ojs238?zeroDateTimeBehavior=convertToNull"
export OJS_DB_USER=slonka_ojs
# OJS_DB_PASSWD needs to be set up manually

export INTERNAL_DB_URL="jdbc:mysql://sql.udl.pl:3306/slonka_io?zeroDateTimeBehavior=convertToNull"
export INTERNAL_DB_USER=slonka_ojs
# INTERNAL_DB_PASSWD needs to be set up manually

export SMTP_HOST=smtp.gmail.com
export SMTP_PORT=465
export SMTP_USER="journalmanager.notification@gmail.com"
# SMTP_PASSWD needs to be set up manually

