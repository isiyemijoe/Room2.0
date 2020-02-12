# Room2.0
android studio project with room capability
1)>> Add dependencies
	  >>  androidTestImplementation "androidx.room:room-testing:$room_version"
	  >>  implementation "androidx.room:room-runtime:$room_version"
    >>  annotationProcessor "androidx.room:room-compiler:$room_version"

2)>> Add to your project level Gradle
	  >> ext {
   	  	 roomVersion = '2.2.1'
   		   archLifecycleVersion = '2.2.0-rc2'
    	  	coreTestingVersion = '2.1.0'
    	  	materialVersion = '1.0.0'
	    	}

3)>> Create an Entity (SQLite Table) 
		>>(Entity are like Object classes)
	      >> Create a new Class Note
		      >>Start with (Before class declaration)
		    	>>@Entity(tableName = "note_table")
	    	>> define the variables
		      	>>@PrimaryKey before Declaration is a declaration of the primary Key
            >>@ NonNull indicate a nonNullabe value
		      	>>@ColumnInfo indicate a column within the table
		      	>> variable defined with @PrimaryKey do not need to declare @ColumnInfo
			
		>> Create a constructor
		>> Create a setter and Getter

4)>> Create a Interface Dao (Datebase access object)
	>> Create a new Interface
		>> Annotate the interface with @Doa
		>> for Insert operations
			>>@Insert(onConflict = OnConflictStrategy.IGNORE)
				>> void.insert(Note note)
		>> for Delete operations
			>>@DELETE
				>>void delete(Note note)

		>> for fetching all the data
			>> @Query("SELECT * FROM note_table ORDER by note_id")
			>> LiveData<List<Note>> getAllNotes();

5)>> Create the Room Database abstract class noteDb
		>> include before class initialization 
			>>@Database(entities = {Note.class}, version =1, exportSchema = false)
		>> the class should extend the RoomDatabase class
			>> initialise an abstract Dao(//myDao) noteDao()
			>> declare a private static volatile noteDb variable called INSTANCE
			>> declare a static final int NUMBER_OF_THREAD = 4;
			>> create a static final ExecutorService
				>>static final ExecutorService databaseWriteExecutor =
       					>> Executors.newFixedThreadPool(NUMBER_OF_THREADS); 
			>> declare a static method getDatabase that returns a noteDb and requires a final Context
				>> if INSTANCE is null
					>> synchronised (noteDb.class){
						if(INSTANCE == null){
							>>INSTANCE = Room.databaseBuilder(context (passed to the method).getApplicationContext(),
								>> noteDb.class, "note_database")
								>> .build();
			
			>> return INSTANCE;



6)>> Create the repositories class (NoteRepositories)
		>> initialize a noteDao mNoteDao
		>>  initialize a LiveData of List of Notes dataSet
		>>  create a constructor the passes Application as a parameter
 			>> NoteDb db = NoteDb.getDatabase(Application(//gotten from the parameter))
			>> mNoteDao = db.noteDao
			>> dataSet = mNoteDao.getAllNotes()
		
		>> Create a getAllNote() that returns a LiveData of List of Note
			>> return dataSet


		>> Create a Insert() with void return type and receives  Note as a parameter
			>> NoteDb.databaseWriterExecutor.execute(() ->{
				mNoteDao.insert(word)
			})

7)>> Create the ViewModel Class
		>> the class should extend the androidViewModel
			>> create a private noteRepository variable noteRepo;
			>> create a LiveData of list of Notes allNote
			>> create a constructor with a parameter of application
				<<super(Applcation)
				<< noteRepo = new NoteRepositories(application)
				<< allNotes  = noteRepo.getallNote
			>> create a method that returns a Livedata of List of word getAllNotes()
				<< return allNote
			>> Create a void method insert that receives a Note Parameter
				<< noteRepo.insert(Note)

7)>> prepare your recyclerView Adapter
	>> Create a new Class noteAdapter
				>> extends RecyclerView.Adapter<>
					>>Create a new class myViewHolder within the class
						>> extend RecyclerView.ViewHolder
							>>implement Methods
			>> add to the <> noteAdapter.myViewHolder
			>> Implement methods
		>> Create a private final LayoutInflater inflater
		>> create a private List<Note> mData
		>> create a constructor for the class that takes only context as a parameter
			>> assign inflater = LayoutInflater.from(context)

		>> In the onCreateViewHolder() 
			>> View itemView = mInflater.inflate(R.layout.recyclerview_item(//Card), parent, false);
      			>> return new WordViewHolder(itemView);
		>> in the OnBindViewHolder
			>>check if mData is not null
				>> assign data to view items
			>> if mData is null
				>> set a dummy text without position
		>>Create a void method setNote that receives a list of Notes
				>> assign mData to the list of Note
				>> notifyDataSetChanged
		
		>> in your getItemCount()
			>> if mData is notnull
				>> return mData.size()
			>> else return 0	
