#!/bin/sh
java -cp "h2-1.4.197.jar:$H2DRIVERS:$CLASSPATH" org.h2.tools.Server -web -webAllowOthers -tcpAllowOthers -tcp
