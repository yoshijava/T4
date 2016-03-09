# T4
Sinopac's T4 DLL wrapper for Java and Python

# Usage
## Java users
You need to install Maven (https://maven.apache.org/) for building the source codes. This wrapper is actually implemented in Java. For Java users, you can directly create a T4 object for buying and selling futures.

## Python users
With py4j, the wrapper is again encapsulated as a socket server. Python users can leverage py4j to transparently call the Java methods as if calling a normal method in Python. For more details, you can refer to https://www.py4j.org/.

There is an example named Gateway.py for showing how to use it. 

## Configuration files.
1. Put a file named <i>account.ini</i> in the working directory. There are 4 lines required by the wrapper:
<pre>
id
password
branch
account
</pre>

2. CA file
Launch your e-leader and download the CA <i>(Sinopac.pfx)</i>. Put it in the working directory.



