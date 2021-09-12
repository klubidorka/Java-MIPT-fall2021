package StringSamples;

import java.io.IOException;

public class SomeClass implements Appendable {
    @Override
    public Appendable append(CharSequence var1) throws IOException{
        return this;
    }

    @Override
    public Appendable append(CharSequence var1, int var2, int var3) throws IOException{
        return this;
    }

    @Override
    public Appendable append(char var1) throws IOException{
        return this;
    }
}