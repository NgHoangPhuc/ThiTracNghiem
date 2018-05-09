package detai.android.Object;

/**
 * Created by hoang on 06-May-18.
 */

public class Lop {
    String name;
    long siso;

    String de;

    public Lop() {
    }


    public Lop(String name) {
        this.name = name;
    }

    public Lop(String name, long siso, String de) {

        this.name = name;
        this.siso = siso;
        this.de = de;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSiso() {
        return siso;
    }

    public void setSiso(long siso) {
        this.siso = siso;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public boolean equals(Object o) {
        return ((Lop) o).getName().equals(name);
    }
}
