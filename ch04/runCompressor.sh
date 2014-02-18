echo "Text" | hadoop jar ./target/ch04-1.0-SNAPSHOT.jar com.myhp.ch04.StreamCompressor org.apache.hadoop.io.compress.GzipCodec | gunzip 
