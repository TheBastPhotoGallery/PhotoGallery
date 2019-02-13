package lv.photogallery.businesslogic.builders.folder;


public class FolderBuilder {
    private Long id;
    private String files;
    private String folderName;
    private String clientEmail;

    private FolderBuilder() {}

    public static FolderBuilder createFolder() {
        return new FolderBuilder();
    }

    public Folder build() {
        Folder folder = new Folder();
        folder.setId(id);
        folder.setFiles(files);
        folder.setFolderName(folderName);
       // folder.setEmail(clientEmail);
        return folder;
    }

    public FolderBuilder withFolderName(String foldername) {
        this.folderName = folderName;
        return this;
    }

    public FolderBuilder withEmail(String email) {
        this.clientEmail = email;
        return this;
    }

    public FolderBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FolderBuilder withFiles(String file) {
        this.files = file;
        return this;
    }

}
