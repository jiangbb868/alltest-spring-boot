rd /S /Q target\classes\static\html
rd /S /Q target\classes\static\js
xcopy /S src\main\resources\static\html\*  target\classes\static\html\*
xcopy /S src\main\resources\static\js\*  target\classes\static\js\*