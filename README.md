Project setup and deployment guide
1)	Clone git repo in your local directory.
2)	Install Eclipse JEE
3)	Use File-> Import-> Projects from Git->Existing Local Repository->Click Add -> Browse ->
4)	Navigate to the directory SER515(cloned from git)
5)	Select SER515 and click Finish.
6)	Select ‘Import as general project’ and make sure you select folder SER515Project_Team3
7)	Click Next.
8)	Click Finish.
9)	Right click on project->Properties->Project Facets
10)	Change Java version to 1.8. 
11)	Select Dynamic Web Module-3.0
12)	Select JavaScript 1.0
13)	Click Apply and then OK.
14)	Right click on Project -> Properties-> Expand JavaScript->IncludePath->Source-> Expand SER515Project_Team3/WebContent
15)	Double click Excluded 
16)	In Exclusion Pattern -> Click on Add-> Browse Web Content/Lib Directory-> Click Ok->Click Finish
17)	Right Click on Project -> Properties-> JavaScript->Validation -> Errors/warnings and uncheck “Strict validation of JavaScript keywords usage”.
18)	Apply and Close.
19) Go to Eclipse preferences -> Validation -> uncheck build for "XML Validator"
19) Go to Eclipse Preferences -> Validation -> Uncheck build for "HTML syntax validator".
20) Click Apply and Close.
21)	Now build the project and Run on Apache Tomcat.
