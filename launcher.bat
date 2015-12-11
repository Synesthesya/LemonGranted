start call Client1.bat

start call Client2.bat

java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://127.0.0.1:8080/ -Djava.security.policy=bin/server/server.policy -cp "bin" server.Server