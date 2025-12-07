[![Build + Test](https://github.com/paveljandejsek/overflow/actions/workflows/maven.yml/badge.svg)](https://github.com/paveljandejsek/overflow/actions/workflows/maven.yml)

# Glowing and Flowing

## Tech info

### Technologies used
    - Java 25
    - Maven
    - Spring Boot 4.0
    - Command line runner

### Open questions
* Was the input and output being in the format in the doc required or example?
* Would there be a better solution to load input from the file, or load input and save output in some structured format a better solution? (Or at least with some user prompts)
* Is this the complete assignment, or do we expect new features/questions in the future?
* Do we care about performance? What are the performance targets?

Due to having no real world analysts/customer to ask I decided to treat the assignment PDF as a "sacred script",
so I used the exact same format for input and output and expected the input and output is meant a Standard input/output 
and implemented a standard behavior for a command line tool - using stdin, stdout and stderr.

### Areas for improvement
#### Packaging
Currently, the application is source code only and requires to have a Java 25 installed with access to some Maven repository.
I was initially hoping to have a native image (built by pipeline for different architectures and released in GitHub releases) 
and/or some docker image (again built by some pipeline using Paketo Buildpacks and Spring Boot Maven plugin) but due to
(partially self-inflicted) time constraints I have not done so.  

Currently, the only CI that is running is testing that the build and tests pass.

#### Performance
Currently, the calculation is done twice - one for each "incident" types, 
thus resulting in O(2n) cyclomatic complexity, I am fairly certain there could be an improvement to O(n)
(with probably maybe a bit less reduced readability) but due to time constraints O(2n) it is.

#### Code Structure
All the code currently sits in one package, in case of further classes needed it will definitely be needed to split it to separate packages.

#### Test coverage
Test coverage is currently really bare bones with only tests for the core computation logic itself and the fact that 
application/spring context is able to run.  
Ideally there would also be tests for some negative use cases, input/output etc...

#### Standard Input/Output processing
Standard Input and Output is being used, but currently hardcoded to System.xxx streams.  
Moving the hardcode to some parameter for the methods in InputService would be advisable - and would improve testability.

#### Usage
Since this is currently built as a command line tool, it would be beneficial to have at least supported args of "--version" and "--help", 
with some small documentation being displayed about the application.

#### Different computational method
The code itself does the computation using a "simple" equation since nothing else was asked in the assignment.  
If however there would be a need for more complex scenarios, comparisons, visualizations, need to take a look at the state in different times of the flow etc.   
there might a value in switching from simple math equation to a discrete simulation of each tank behavior during the passage of time.  
(Technically probably a list of some tank state representations while simulating the passage of time.)

### Implementers fuckups
While the assignment should have taken 3-4 hours, I will confess that it took me 4 hours only to set up the dev environment :)
(I had a freshly installed laptop)  

If you want to take a laugh on my account please do keep reading :) 

#### GPG
First issue I had was with the GPG - I decided I wanted to use my already existing keys that I would migrate from Windows machine...  
Well migration did not work, exporting importing did not work, in the end I think the issue is somewhere in the pinentry-mac package or its setup but no clue...  

After like 3 hours I just gave up, created a new keypair and imported the public key into GitHub, and now it mostly works
(I still have issue with the pinentry not coming up with the GUI, but if I enter the passphrase into terminal before commiting with IDEA it works - good enough for now)

#### New Java vs Old IDEA
Since I stopped paying for IntelliJ IDEA from March 2025 I used a 2023.3 that was the last available to me.
While the initial project setup for Java 25 went without a hitch (IDEA even downloaded the JDK for me), when trying to build the project from IDEA (and not from Maven itself) I received a lot of angry errors about Java version 0 not supported etc...  
So I decided to downgrade to 21, change the version in Maven, download new JDK, change the version in project settings...

#### Lombok
Instead of smooth sailing from now on what occurred next though was lots of errors regarding missing constructors...  
Quick GoogleFu revealed that IDEA 2023 had a bug and the solution to this bug is to upgrade to IDEA 2024 which I did not have access to....  
So I bit the bullet, bought a new monthly subscription and happily went on to roll back the Java 21 downgrade again :)

#### New IDEA UI
Now I knew this would come to have bitten me in the ass eventually, but since I postponed on using the new IDEA UI _till now_ 
I struggled quite a bit more than usually - but I still stand by my opinion that the old "Version Control" panel at the bottom was much more friendly than the new nonmodal "Commit" sidebar...

