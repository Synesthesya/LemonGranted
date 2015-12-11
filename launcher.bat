start java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://127.0.0.1:8080/ -Djava.security.policy=bin/server/server.policy -cp "bin" server.Server

start java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://127.0.0.1:8081/ -Djava.security.policy=bin/launcher/client.policy -cp "bin" launcher.Start

java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://127.0.0.1:8081/ -Djava.security.policy=bin/launcher/client.policy -cp "bin" launcher.Start