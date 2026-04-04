package oop.step11;

public final class Computer {
    // Mandatory fields
    private final String cpu;
    private final int ram;

    // Optional fields
    private final int storage;
    private final String gpu;
    private final String os;

    private Computer(Builder builder) {
        this.cpu     = builder.cpu;
        this.ram     = builder.ram;
        this.storage = builder.storage;
        this.gpu     = builder.gpu;
        this.os      = builder.os;
    }

    @Override
    public String toString() {
        return "Computer{cpu='" + cpu + "', ram=" + ram +
               ", storage=" + storage + ", gpu='" + gpu + "', os='" + os + "'}";
    }

    public static class Builder {
        // Mandatory
        private final String cpu;
        private final int ram;

        // Optional with defaults
        private int storage = 256;
        private String gpu  = "なし";
        private String os   = "なし";

        public Builder(String cpu, int ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder os(String os) {
            this.os = os;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
