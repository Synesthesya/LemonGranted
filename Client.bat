@echo off

java -Djava.rmi.server.logCalls=false -Djava.rmi.server.codebase=http://127.0.0.1:8081/ -Djava.security.policy=bin/launcher/client.policy -cp "bin" launcher.Start

exit