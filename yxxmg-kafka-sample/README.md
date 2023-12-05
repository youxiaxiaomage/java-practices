# kafka相关知识介绍以及应用

### kafka 副本信息

Kafka 为分区引入了多副本（Replica）机制，通过增加副本数量可以提升容灾能力。同一分区的不同副本中保存的是相同的消息，每个分区都有一个
leader 副本和多个 follower 副本，其中leader副本负责处理读写请求，follower副本只负责与leader副本的消息同步。副本处于不同的broker中，当leader副本出现故障时，Kafka
会自动从 follower 副本中选举一个新的 leader 副本对外提供服务。这个过程称为副本重分配。在副本重分配期间，Kafka
仍然可以正常工作，但可能会出现一些延迟。Kafka通过多副本机制实现了故障的自动转移，当Kafka集群中某个broker失效时仍然能保证服务可用。

Kafka 的副本机制可以提供以下好处：
① 数据可靠性：即使某个副本失效，数据仍然可以从其他副本中恢复。
② 高可用性：即使某个副本失效，Kafka 仍然可以正常工作。
③ 提高读取性能：由于可以从任意一个副本中读取消息，因此可以提高读取性能。
Kafka 消费端也具备一定的容灾能力。Consumer
使用拉（Pull）模式从服务端拉取消息，并且保存消费的具体位置，当消费者宕机后恢复上线时可以根据之前保存的消费位置重新拉取需要的消息进行消费，这样就不会造成消息丢失。

### kafka有哪些特点?

1. 顺序读写
2. 零拷贝
3. 消息压缩
4. 分批发送

### ISR、OSR、AR是什么?

1. ISR(in-sync Replicas)
   与Leader副本保持同步的副本集合。当Producer发送消息到Kafka集群时，只有ISR的副本才会写入消息，确保数据的可靠性和一致性。如果ISR中的某个副本无法与Leader同步，则该副本将被从ISR中移除，直接与Leader重新同步。
2. OSR(Out-of-Sync Replicas) 指的是与Leader副本失去同步的副本集合。当ISR中某个副本无法与Leader保持同步时，该副本将被移动到OSR中，直到与Leader重新同步。
3. AR(Assigned Replicas) 指的是被分配到某个Partition的副本集合。AR包括ISR和OSR。
   ISR是kafka中保证数据可靠性和一致性的重要机制，而AR和OSR则与Partition相关的副本集合。分区中所有副本统称为AR。所有与leader副本保持一定程度同步的副本（包括leader副本在内）组成ISR，ISR集合是AR集合中的一个子集。消息会先发送到leader副本，然后follower副本才能从leader副本中拉取消息进行同步，同步期间内follower副本相对于leader副本而言会有一定程度的滞后。前面所说的“一定程度的同步”是指可忍受的滞后范围，这个范围可以通过参数进行配置。与leader副本同步滞后过多的副本（不包括leader副本）组成OSR，由此可见，AR=ISR+OSR。在正常情况下，所有的
   follower 副本都应该与 leader 副本保持一定程度的同步，即AR=ISR，OSR集合为空。
   leader副本负责维护和跟踪ISR集合中所有follower副本的滞后状态，当follower副本落后太多或失效时，leader副本会把它从ISR集合中剔除。如果OSR集合中有follower副本“追上”了leader副本，那么leader副本会把它从OSR集合转移至ISR集合。默认情况下，当leader副本发生故障时，只有在ISR集合中的副本才有资格被选举为新的leader，而在OSR集合中的副本则没有任何机会。

### kafka 中 ISR的伸缩指什么？

ISR中的伸缩指的是ISR中副本的动态变化。当某个副本落后于leader副本时，它将被从ISR中移除，直到它再次与leader副本保持同步。当某个副本追上了leader副本，它将被重新加入ISR中。这种动态变化可以通过Kafka的副本管理机制自动完成，从而保证ISR中的副本集合始终与leader副本保持同步。

### 什么情况下一个broker会从ISR中踢出去？

ISR（In-Sync
Replicas）是Kafka中用于保证数据可靠性和高可用性的机制。ISR中的副本与Leader副本保持同步，可以保证在Leader副本宕机时，ISR中的副本可以顶替成为新的Leader副本，从而保证数据的可靠性和高可用性。
一般情况下，一个broker会从ISR中踢出去有以下几种情况：

1. 副本同步延迟：如果ISR中的副本与Leader副本之间的同步延迟超过了一定的阈值，那么该副本就会被从ISR中踢出去。
2. 副本故障：如果ISR中的副本发生故障，比如网络故障、硬件故障等，那么该副本也会被从ISR中踢出去。
3. 副本过期：如果ISR中的副本长时间没有与Leader副本保持同步，那么该副本也会被从ISR中踢出去。

### kafka 副本和ISR扮演什么角色？

副本（Replicas）和ISR（In-Sync Replicas）是Apache Kafka中非常重要的概念，它们扮演着以下角色：

1. 副本：Kafka中的每个分区都有多个副本，每个副本都是分区数据的完整拷贝。副本的作用是提高数据的可靠性和可用性。当某个副本所在的broker宕机时，其他副本可以继续提供服务，确保数据不会丢失。
2.

ISR：ISR是指与leader副本保持同步的副本集合。当leader副本发生故障时，ISR中的某个副本会被选举为新的leader副本。只有在ISR中的副本才能被选举为新的leader副本，因为它们保证了数据的一致性。如果某个副本与leader副本失去同步，它将被从ISR中移除，直到与leader副本重新保持同步。
因此，副本和ISR是Kafka实现高可用性和数据一致性的重要手段。

### kafka 副本长时间不在ISR中，意味着什么？

在Kafka中，ISR（in-sync
replicas）是指与leader副本保持同步的副本集合。如果一个副本长时间不在ISR中，意味着该副本与leader副本的数据同步出现了问题，可能是由于网络故障、硬件故障或其他原因导致的。这种情况下，该副本可能会落后于其他副本，导致数据不一致。因此，Kafka会将该副本从ISR中移除，以避免数据不一致的情况发生。当该副本恢复正常后，Kafka会将其重新加入ISR中，以确保数据同步。

### kafka follower副本如何和 leader副本同步？

Kafka中的follower副本通过与leader副本进行数据同步来保持与leader副本的一致性。follower节点与leader节点同步的过程如下：

1. follower节点向leader节点发送拉取请求，请求获取最新的数据。
2. leader节点接收到拉取请求后，将最新的数据发送给follower节点。
3. follower节点接收到数据后，将其写入本地日志文件，并向leader节点发送确认消息，表示已经成功接收到数据。
4. leader节点接收到确认消息后，将该消息标记为已经被follower节点接收到。
5. follower节点定期向leader节点发送心跳消息，以保持与leader节点的连接。
6. 如果follower节点在一定时间内没有向leader节点发送心跳消息，或者leader节点在一定时间内没有收到follower节点的确认消息，那么leader节点将认为该follower节点已经失效，将其从副本集合中移除。

### kafka 如何实现主从同步？

Kafka通过副本机制来实现主从同步。每个分区都有多个副本，其中一个副本被指定为领导者（leader），其他副本被指定为追随者（follower）。领导者负责处理所有的读写请求，而追随者只是简单地复制领导者的数据。
当生产者向Kafka发送消息时，它会将消息发送到领导者副本。领导者副本将消息写入本地日志，并将消息复制到所有追随者副本。一旦所有追随者副本都确认已成功复制消息，领导者副本将向生产者发送确认消息。
当消费者从Kafka读取消息时，它会从领导者副本读取数据。如果领导者副本不可用，消费者可以从追随者副本读取数据。Kafka使用ZooKeeper来管理领导者和追随者副本之间的切换，以确保高可用性和数据一致性。

### Kafka 控制器的选举？

Kafka的控制器其实也是一个broker，只不过除了提供一般的broker功能之外，它还负责分区的首领选举。

1. 控制器的选举是通过ZooKeeper实现的，只有一个broker可以成为控制器，其他broker会监听控制器节点的变化，一旦控制器节点发生变化，其他broker就会重新选举控制器。
   集群中第一个启动的broker会通过在ZooKeeper中创建一个名为/controller的临时节点让自己成为控制器。其他broker在启动时也会尝试创建这个节点，但它们会收到“节点已存在”异常，并“意识”到控制器节点已存在，也就是说集群中已经有一个控制器了。其他broker会在控制器节点上创建ZooKeeper
   watch，这样就可以收到这个节点的变更通知了。我们通过这种方式来确保集群中只有一个控制器。
2. 如果控制器被关闭或者与ZooKeeper断开连接，那么这个临时节点就会消失。控制器使用的ZooKeeper客户端没有在zookeeper.session.timeout.ms
   指定的时间内向ZooKeeper发送心跳是导致连接断开的原因之一。当临时节点消失时，集群中的其他broker将收到控制器节点已消失的通知，并尝试让自己成为新的控制器。第一个在ZooKeeper中成功创建控制器节点的broker会成为新的控制器，其他节点则会收到“节点已存在”异常，并会在新的控制器节点上再次创建ZooKeeper
   watch。
3.

每个新选出的控制器都会通过ZooKeeper条件递增操作获得一个数值更大的epoch。其他broker也会知道当前控制器的epoch，如果收到由控制器发出的包含较小epoch的消息，就会忽略它们。这一点很重要，因为控制器会因长时间垃圾回收停顿与ZooKeeper断开连接——在停顿期间，新控制器将被选举出来。当旧控制器在停顿之后恢复时，它并不知道已经选出了新的控制器，并会继续发送消息——在这种情况下，旧控制器会被认为是一个“僵尸控制器”。消息里的epoch可以用来忽略来自旧控制器的消息，这是防御“僵尸”的一种方式。

### Kafka 控制器负责分区的首领选举

控制器必须先从ZooKeeper加载最新的副本集状态，然后才能开始管理集群元数据和执行首领选举。
当控制器发现有一个broker离开了集群时，原先首领位于这个broker上的所有分区需要一个新首领。它将遍历所有需要新首领的分区，并决定应该将哪个分区作为新首领（可能就是副本集中的下一个副本）。然后，它会将更新后的状态持久化到ZooKeeper中，再向所有包含这些分区副本的broker发送一个LeaderAndISR请求，请求中包含了新首领和跟随者的信息。每一个新首领都知道自己要开始处理来自生产者和消费者的请求，而跟随者也知道它们要开始从新首领那里复制消息。集群中的每一个broker都有一个MetadataCache，其中包含了一个保存所有broker和副本信息的map。控制器通过UpdateMetadata请求向所有broker发送有关首领变更的信息，broker会在收到请求后更新缓存。在启动broker副本时也会有类似的过程——主要的区别是broker所有的分区副本都是跟随者，并且需要在自己有资格被选为首领之前与首领保持同步。
总的来说，Kafka会使用ZooKeeper的临时节点来选举控制器，并会在broker加入或退出集群时通知控制器。控制器负责在broker加入或退出集群时进行首领选举。控制器会使用epoch来避免“脑裂”。所谓的“脑裂”，就是指两个broker同时认为自己是集群当前的控制器。

### kafka有哪些地方需要选举，这些地方的选举策略有哪些？

1. Controller选举
   Controller是Kafka集群中的一个节点，负责管理集群的元数据信息，包括Broker的上下线、Partition的分配等。当当前的Controller节点失效时，需要选举一个新的Controller节点来接管其职责。
   Controller选举的策略是通过Zookeeper实现的，每个Kafka
   Broker都会在Zookeeper上创建一个临时节点，节点的路径为/brokers/ids/[broker-id]
   ，其中broker-id为Broker的唯一标识。当一个Broker想要成为Controller时，它会在Zookeeper上创建一个临时节点/brokers/controller_epoch，节点的值为当前的epoch值，然后尝试获取/brokers/controller节点的锁。如果获取锁成功，则该Broker成为新的Controller节点；否则，它会监听/brokers/controller节点的变化，等待锁的释放。
2. Partition Leader选举
   每个Partition在Kafka集群中都有一个Leader节点，负责处理该Partition的读写请求。当当前的Leader节点失效时，需要选举一个新的Leader节点来接管其职责。
   Partition
   Leader选举的策略是通过副本之间的协作实现的。每个Partition都有多个副本，其中一个副本为Leader，其他副本为Follower。当Leader节点失效时，Follower节点会发起一次选举，选举出一个新的Leader节点。
   具体的选举过程如下：

   Follower节点向所有其他副本发送一个Leader选举请求，请求中包含该Partition的最后一条消息的offset值。
   如果其他副本中有一个副本的最后一条消息的offset值大于等于该Follower节点的offset值，则该副本成为新的Leader节点。
   如果没有副本的offset值大于等于该Follower节点的offset值，则该Follower节点等待一段时间后重新发起选举请求。

需要注意的是，为了避免脑裂（split-brain）的情况发生，Kafka要求每个Partition至少有一个副本处于活跃状态，否则该Partition将无法使用。因此，在进行Leader选举时，只有那些处于活跃状态的副本才能参与选举。

### kafka Partition Leader选举

Kafka是一个分布式的消息系统，它将消息分成多个分区（Partition）并存储在多个Broker上。每个分区都有一个Leader和多个Follower，Leader负责处理读写请求，Follower则从Leader同步数据。
当一个Broker宕机或者网络故障导致Leader无法正常工作时，Kafka需要进行Partition Leader选举，选出一个新的Leader来处理读写请求。选举的过程如下：

1. 每个Broker都会定期向Zookeeper注册自己的Broker信息，并创建一个临时节点。这个节点的路径是/brokers/ids/broker-id，节点的值是一个JSON格式的字符串，包含了Broker的IP地址、端口号等信息。
2. 当一个Broker宕机或者网络故障导致Leader无法正常工作时，Zookeeper会检测到这个Broker的临时节点被删除，然后通知其他Broker。
3.

其他Broker会检查所有的Partition，如果某个Partition的Leader是宕机的Broker，那么它会尝试成为新的Leader。它会向Zookeeper创建一个临时节点/brokers/topics/topic/partition/broker-id
，表示它想要成为这个Partition的Leader。

4. 如果多个Broker都尝试成为Leader，那么Zookeeper会根据节点创建时间的先后顺序来选举Leader。创建时间最早的节点会成为新的Leader。
5. 选举完成后，新的Leader会向Zookeeper更新Partition的元数据，其他Broker会从Zookeeper获取最新的元数据，并更新自己的缓存。
   需要注意的是，Kafka的Partition
   Leader选举是异步的，也就是说，选举完成后，可能会有一段时间内某些消息无法被正常处理。因此，Kafka的高可用性需要依赖于多个副本（Replica）的存在，以保证即使某个Broker宕机，也能够保证消息的可靠性和可用性。

### LEO、HW、LSO、LW分别代表什么意思?

1. LSO log stable offset 上次稳定偏移 和kafka事务相关的一个偏移量
2. LW low water mark 低水位 用于控制消息可见性
3. HW High water mark
   高水位 用于控制消息可见性，它标识了一个特定的偏移量（offset)，消费者只能拉取这个offset之前的消息。LEO是Log End
   Offset的缩写，它标识当前日志文件中下一条待写入消息的offset
4. LEO Log end offset 消息终止偏移 kafka分区消息的终点

### 如何实现消息的有序性?

### 在什么情况下会出现消息丢失?

### 数据传输的事务有哪些?

### 每个分区只能被一个消费者线程消费，如何做到多线程同时消费一个分区?

### 在什么情况下会出现消息丢失?

### 消费者是否可以指定分区消息?

### kafka消息是采用Pull模式，还是Push模式?

### kafka 分区数可以增加或减少吗?为什么?

### kafka 新旧消费者的区别?

### 零拷贝?

针对程序运行中不可变的数据或者不可变的部分尽量减少或者取消内存数据的拷贝，用内存地址去引用这些数据  
减少了JVM堆内存占用，降低了GC导致的服务暂停和OOM风险
减少了大批量频繁内存拷贝的时间，能大幅度优化数据吞吐性能

### kafka的rebalance?