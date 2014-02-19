package AdvancedMapReduce.sequence.demo1;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * what if we want to emit Point3D objects as keys too?
 *
 * In Hadoop MapReduce, if (key, value) pairs sent to a single reduce task include multiple keys,
 * the reducer will process the keys in sorted order. So key types must implement a stricter interface, WritableComparable.
 * public class Point3D implements WritableComparable {
 *     public int compareTo(Point3D other) {}
 *     public int hashCode()
 * }
 * It is important for key types to implement hashCode() as well; the section on Partitioners later in this module explains why.
 * See Point3DKeyType.java
 */
public class Point3D implements Writable {
    public float x;
    public float y;
    public float z;

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D() {
        this(0.0f, 0.0f, 0.0f);
    }
    //Serialize the fields of this object to out
    public void write(DataOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
    }
    /**
     * Deserialize the fields of this object from <code>in</code>.
     *
     * <p>For efficiency, implementations should attempt to re-use storage in the
     * existing object where possible.</p>
     *
     * @param in <code>DataInput</code> to deseriablize this object from.
     * @throws IOException
     */
    public void readFields(DataInput in) throws IOException {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
    }

    public String toString() {
        return Float.toString(x) + ", "
                + Float.toString(y) + ", "
                + Float.toString(z);
    }
}
