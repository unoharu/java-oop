package oop.step11;

public enum Day {
    MONDAY {
        @Override
        public String work() { return "週初めのミーティング"; }
    },
    TUESDAY {
        @Override
        public String work() { return "開発作業"; }
    },
    WEDNESDAY {
        @Override
        public String work() { return "コードレビュー"; }
    },
    THURSDAY {
        @Override
        public String work() { return "テスト実施"; }
    },
    FRIDAY {
        @Override
        public String work() { return "週末の振り返り"; }
    },
    SATURDAY {
        @Override
        public String work() { return "休日です"; }
    },
    SUNDAY {
        @Override
        public String work() { return "休日です"; }
    };

    public abstract String work();

    boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }
}
