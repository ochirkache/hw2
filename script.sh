#!/bin/bashSS

POSTFIX=("localhost pulseaudio.desktop: W: [pulseaudio] main.c: This program is not intended to be run as root (unless --system is specified)."
          "localhost gnome-session-binary[2187]: WARNING: Application 'org.gnome.SettingsDaemon.Housekeeping.desktop' killed by signal 15")

hdfs dfs -rm -r input
hdfs dfs -rm -r output
hdfs dfs -put ~/Desktop/ABESHOV_HW2/input input
/opt/spark/bin/spark-submit --class bigdata.SparkApp --master local ~/Desktop/ABESHOV_HW2/target/ABESHOV_HW2-1.0-SNAPSHOT.jar \
  hdfs://localhost:9000/user/root/input hdfs://localhost:9000/user/root/output/

hdfs dfs -cat output/part-00000
