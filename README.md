# Apache-Strom

### Installation

## 1. Install Java on your system, if you don’t have it already.
    
   To install Java, first update the package index.
   ```
   sudo apt update
   sudo apt install default-jre
   ```
   Check Java Version
   ```
   java -version
   ```
## 2. Install ZooKeeper framework.
  
  ZooKeeper stores all configuration and state data to disk. So, you will need to create a directory structure for ZooKeeper with the following command:
  ```
  mkdir -p /data/zookeeper
  ```
  
  Next, give proper ownership to the /data directory with the following command:
  ```
  chown -R zookeeper:zookeeper /data/zookeeper
  ```

  Next, change the directory to the /opt and download the latest version of ZooKeeper with the following command:
  ```
  cd  /opt
  wget https://archive.apache.org/dist/zookeeper/zookeeper-3.4.9/zookeeper-3.4.9.tar.gz
  ```

  Next, extract the downloaded file with the following command:
  ```
  tar -xvzf zookeeper-3.4.9.tar.gz
  ```
  Next, rename the extracted binary with the following command(optional):
  ```
  mv zookeeper-3.4.9 zookeeper
  ```
  Next, give ownership to the ZooKeeper user with the following command:
  ```
  chown -R zookeeper:zookeeper /opt/zookeeper
  ```
  Configure ZooKeeper
  Next, you will need to create a configuration file for ZooKeeper. You can do it with the following command:
  ```
  nano /opt/zookeeper/conf/zoo.cfg
  ```
  Add the following lines:
  ```
  tickTime=2500
  dataDir=/data/zookeeper
  clientPort=2181
  maxClientCnxns=80
  ```
  Save and close the file, when you are finished.
  Next, start the ZooKeeper service with the following command:
  ```
  cd /opt/zookeeper
  bin/zkServer.sh start
  ```
  You should see the following output:
  ZooKeeper JMX enabled by default
  Using config: /opt/zookeeper/bin/../conf/zoo.cfg
  Starting zookeeper ... STARTED

## 3. Install Apache Storm framework.
  
  The Website for download is: http://storm.apache.org/downloads.html (Kindly download the stable release for better performance)
  ```
  mkdir apache-storm
  cd apache-storm
  wget http://redrockdigimark.com/apachemirror/storm/apache-storm-1.1.0/apache-storm-1.1.0.tar.gz
  ```
  Here I am downloading Apache Storm 1.1.0
  ```
  tar -xvf apache-storm-1.1.0.tar.gz 
  ```
  Creating data Directory and editing YAML file for storm.
  ```
  cd apache-storm-1.1.0
  mkdir data/
  nano conf/storm.yaml
  ```
  add/modify the following lines.
  ```
  Storm.zookeeper.servers:
   - “127.0.0.1”
  storm.local.dir: “/path/to/storm/data”
  nimbus.host: “127.0.0.1”
  supervisor.slots.ports:
   - 6700
   - 6701
   - 6702
   - 6703
  ```
  Add Storm to PATH VARIABLES.
  ```
  nano ~/.bashrc
  ```
  Add the below line to the end of the line.
  ```
  Export STORM_HOME=/path/apache-storm/apache-storm-1.1.0
  export PATH=”$PATH:$STORM_HOME/bin”
  ```
  Start Apache Storm Services by following Commands.
  ```
  zkServer.sh start 
  storm nimbus & storm supervisor & storm ui & 
  ```
  Open the Browser to Check the Storm UI.
  ```
  http://localhost:8080
  ```