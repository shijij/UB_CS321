class Course {
    private String name = null;
    private Prof prof = null;

    public Course(String aName, Prof aProf) {
        super();
        name = aName;
        prof = aProf;
    }
    public String getName() {
        return name;
    }
    Prof getProf() {
        return prof;
    }
}
