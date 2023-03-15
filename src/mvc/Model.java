package mvc;
/*
Edits:
    Sanjana 3/14/23: created file

*/
public class Model extends Bean{
    Boolean unsavedChanges;
    String fileName;

    public Model(Boolean unsavedChanges, String fileName){
        this.unsavedChanges = unsavedChanges;
        this.fileName = fileName;
    }
}
