# T4
Sinopac's T4 DLL wrapper for Java and Python

# Usage
## Java users
You need to install Maven (https://maven.apache.org/) for building the source codes. This wrapper is actually implemented in Java. For Java users, you can directly create a T4 object for buying and selling futures.

## Python users
With py4j, the wrapper is again encapsulated as a socket server. Python users can leverage py4j to transparently call the Java methods as if calling a normal method in Python. For more details, you can refer to https://www.py4j.org/, but believe me, you don't have to.

There is an example named Gateway.py for showing how to use it. <b>For Python users, remember to start Java side for being called from Python.</b>

## Configuration files
1. Put a file named <i>account.ini</i> in the working directory. There are 4 lines required by the wrapper:
<pre>
id
password
branch
account
</pre>

2. CA file </br>
Launch your e-leader and download the CA <i>(Sinopac.pfx)</i>. Put it in the working directory.

3. The last thing you need to set up is configs/Configuration.json. For example:
<pre>
 {
    "configuration": {
          "COMMODITY": "MXFL5",
     }
 }
</pre>
where MXF<b>L5</b> stands for:
<pre>
L = December. It is the 12th of the alphabets.
5 = 2015. 
</pre>
