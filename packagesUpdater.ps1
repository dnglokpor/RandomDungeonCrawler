Remove-Item ".\Confrontation\*",".\Defines\*",".\WorldBasics\*"
javac -d . ".\sources\Defines\*.java", ".\sources\WorldBasics\*.java", ".\sources\Confrontation\*.java"