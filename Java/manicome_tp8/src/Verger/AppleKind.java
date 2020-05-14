package Verger;

public enum AppleKind {
    Golden, PinkLady, GrannySmith;


    @Override
    public String toString() {
        String name = switch(this) {
            case Golden -> "Golden";
            case PinkLady -> "Pink Lady";
            case GrannySmith -> "Granny Smith";
        };

        return name;
    }
}
