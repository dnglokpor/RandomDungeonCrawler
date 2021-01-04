cd "C:\myLewysG\Logiciels\Mes Tests\RandomDungeonCrawler"
del ".\Confrontation\*" ".\Defines\*" ".\WorldBasics\*"
javac -d . ".\sources\Defines\*.java" ".\sources\WorldBasics\*.java" ".\sources\Confrontation\*.java"
pause