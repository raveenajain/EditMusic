Music Editor


Instructions 

Enter file path first then “orig” (for our code) or “prov” (for the code we received). If you chose the providers code, enter comp. Otherwise enter on of our four views:
    to render the Console View -> console 
    to render the Midi View -> midi 
    to render the Gui View -> visual 
    to render the Composite View -> comp

In Original Composite and Gui Views
    to jump to the beginning** -> h
    to jump to the end** -> e 
    to pause and unpause the piece -> p
    to add a note -> a (then follow further instructions in pop-up)
    to add a repeat -> (then follow further instructions in pop-up)
    to remove a note -> click on start beat of note 

In Provider’s Composite and Gui Views
    to jump to the beginning -> h
    to jump to the end -> e 
    *** missing functionality explained in Code Review txt 

** we use h and e keys instead of home and end keys because those were already built in and would not allow us to jump to the areas we wanted 

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Extra Credit

-added repeat class which allowed us to neatly allow for repeats in our music.
-added key and modified code for enter key in createKeyHandler in the MusicController.
-added repeat methods to the model and midi and gui views.
-in guiView renamed addPopUp to addNotePopUp. 

HW8

-changed main so that it does not take in console inputs.
-changed fields to protected in MusicController for adapter.

For clients: 
-moved printLowestToHighest() and listOfPO() implementations into console and gui panel classes therefore removing them from the model
-made getLowestThenHighest public as it is needed in our model and is also needed for the console and gui views

For Providers:
-added five additional classes to implement the providers code

HW7

Renamed MusicView to ConsoleViewImpl. 
Renamed GuiViewFrame to GuiViewImpl and now implements the GuiView.

Changes Made in Model
    -removed getModelState() 
    -removed private helpers from getModelState()

Changes Made in MusicEditor Class
    -use System.in to allow for console input 

Changes Made in Controller (Project and Interface/Class)
    -added keyboard and mouse handler classes
    -added option for composite view in createView()
    -added methods to create keyboard and mouse handlers

Changes Made in ConsoleView
    -directly added method that was getModelState() in the Model project classes to this class’ drawModel() 
    -added the private helpers for getModelState() from the model

Changes Made in ConcreteGuiViewPanel
    -rewrote paintComponent() to be based off a constant 
    -in paintComponent added white line (that will be animated)
    -added methods to set the white line’s coordinates based on outside information
    -added methods to help return locations of where things were drawn

Changes Made IMusicView
    -added jump home and end methods 
   
Changes Made in MidiViewImpl
    -added pause (boolean, true if composition is paused) and tickPos (int, sequencer’s tick position) fields 
    -added jump home and end methods and a pausing method
    -added getters for the tick position field and the pause field

Changes Made in MidiMock
    -added jump home and end methods 

HW6

Changes Made in Model
    -made fields private and added setter methods and getter methods for access 
    -added tempo field 
    -allNotes made public as it was useful in the view 
    -added nested builder class thats builds a MusicModel from readable files 
    -removed getAtBeat() as it was unnecessary repeat code
    -changed editNote() to remove unnecessary code and make the method more efficient
    -removed moveNote() as it was just calling editNote() and was never used  
    -getLastBeat() made public as it was needed in the view
    -removed try catches and now throw exceptions. we thought they should be thrown now and then caught later on when working with user input and output 
    -changed tests accordingly 
    -added tests for setters and getters and to check if the output is still correct if notes overlap
    -now using a StringBuilder for printLowestToHighest() and getModelState()

Changes Made in MusicNote and Pitch
    -made fields private and added setter methods and getter methods for access 
    -added instrument and volume fields 
    -duration now includes 0
    -removed an accidentally added E# note 
    -changed tests accordingly 
    -added tests for setters and getters 

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Pitch - Enum Class 
    This is an enumeration of the 12 possible pitches ranging from C (the lowest) to B (the highest).
    toString is necessary to output each element of this enum as a String. 
    Using an enumeration creates strict guidelines for what pitches are allowed. 

NoteType - for Provider Implementation - Enum Class
    This is an enumeration of 2 possible note types-HEAD to denote the start of a note and SUSTAIN to denote a note that makes up the duration.  
	We needed to create this in order for their immutable model to compile and was therefore needed in our adapter. 

MusicNote - Class -> Fields
    Four elements (represented as fields) make up a MusicNote:
	pitch -> one of the twelve enumerated elements from the Pitch class 
	octave -> an int that ranges from -99 to 999*
	duration -> a positive int (greater than 0)
		   -> how long a note will be sustained 
		   -> begins at startBeat (i.e a note with a duration of 4 starting at 0 will play at beat 0, beat 1, beat 2, and beat 3)
	startBeat -> a positive int (including 0)
		    -> what beat the note will begin at  
    validNote (private) checks for the conditions mentioned above.
    These four fields allow notes to be distinct and provide enough information for when they need to be played. 
  
MusicNote - Class -> Methods
    equals (as well as hashCode) is overridden in this class to allow to two notes to be compared to see if they are the same. 
    isLowerNote and isHigherNote both return booleans, checking to see if this note is lower or higher, respectively, to the given MusicNote. 
	This method is used in the MusicModel to find the lowest and highest notes in the entire model. 
    combinePitchOctave combines this notes’ pitch and octave into a single string.
	These two fields need to be combined because a note is represented by both of these elements together (i.e C#4 is a different note then C#6). 
    isStartOfNote returns true if the given int is the startBeat of this note. 
	Is useful because, in the model, all the beats of a note are hashed to an Integer. This way we know specifically when a note begins vs. when it is just playing.

Note - for Provider Implementation - Class
    This class just constructs a note. We needed it for the midi view to compile as new notes are made for the sequencer.

Repeat - Class
    This class constructs a repeat which is just a start and an end int. It also has a hasRepeated field which allows us to only go through a repeat once. It also contains getter 
    and setter methods and a method to check for a valid repeat (start beat must be less than end beat, both must be less than the last beat of a music piece, and both must 
    divisible by the music piece’s beatspermeasure). 
 
IMusicController - Interface -> Methods 
    getModel returns the Model field of the class (MusicController) implementing this interface.
    setModel sets the model field to the given model as long as it is not null.
    createViewModel creates a ViewModel version of the model field. 
    createView creates a new IMusicView depending on the user input (gui, visual, midi, composite). 
    createKeyboardHandler creates a new KeyboardHandler with the ability to pause, jump to the beginning or end of, and add a note or repeat to the composition.
    createMouseHandler creates a new MouseHandler with the ability to remove one note at a time. 

MusicController - Class -> Fields
    model and view -> an IMusicModel and IMusicView
	Both are updated in this class. This allows the user to create a new view and use the model’s data without letting the model be mutated by the user through the view. 

Controller Adapter - for Provider Implementation - Class
    This class only overrides createView in order to make the providers gui or composite views. 
	Instead of adding another else if in the createView method in MusicController, we found it was neater to make a new class. 

KeyBoard Handler - Class
    Three Map<Integer, Runnable> fields:
	-> one for typed keys, one for pressed keys, and one for released keys. 
    Overrides all KeyListener methods. 
    getTyped, getPressed, and getReleased return the values of their respective fields. 

Mouse Handler - Class 
    Three fields:
	X and Y -> ints representing the X and Y coordinates of where the mouse is pressed. 
	clicked -> Map<Integer, Runnable> for when the mouse is clicked. 
    Overrides all MouseListener methods. 
    setClicked and getClicked set and get the clicked field respectively. 
    getX and getY return the values of their respective fields. 

MusicReader - Class
    This contained a method that parses through a file containing all necessary information and creates a model with that data. 

IMusicModel - Interface
    addNote allows the given MusicNote to be added to the model.
    addNotes allows a list of notes to be added to the model. 
	This expedites the process because adding one note at a time, for example, 50 times, would be tedious.
    allNotes returns a list of all the notes in this model.
	This makes it easier to check for notes as they are initially split up amongst different ArrayLists hashed to different Integers. 
    removeNote allows the given MusicNote to be removed from the model.
    removeNotes allows a list of notes to be removed from the model. 
	This expedites the process because removing one note at a time, for example, 50 times, would be tedious.
    editNote changes the given note depending on the given parameters.
	Each parameter is a field of MusicNote. That way, each field can be changed individually so, for example, the startBeat and octave can stay the same using this… while the
	pitch and duration can be individually changed.
    getLastBeat gets the last beat that a note exists at/the last key value of the noteList. 
	This is useful for the model and drawing.   
    mergeModels and addModelAtEnd combine this MusicModel with the given MusicModel with the former merging the two models and the latter appending that model to the end of this.  
    getLowestThenHighest returns a two element ArrayList with the first element being the lowest pitch/octave combination and the second being the highest.
	This makes getting the range of pitch/octave combinations possible.
    getBeatsPerMeasure returns this model’s beatsPerMeasure field value. 
    getNotelist returns the model’s notelist field value. 
    getTempo returns the model’s tempo field value. 
    getRepeats returns the model’s repeat field value. 
    setNoteList allows the notelist to be changed to the given ArrayList. 
    setTempo changes the tempo to the given int. 
    setRepeats changes the repeats to the given ArrayList. 
    checkNestedRepeats returns true if the given repeat would be nested amongst the other repeats of the piece. 

Composition Builder - Interface -> Methods
    build acts as a last step, creating the MusicModel. 
    setTempo sets the model’s tempo to the given int. 
    addNote adds a note with the given information to the model. 
	This is necessary for giving the model note data/creating the model’s notelist.

MusicModel - Class -> Fields
    a MusicModel is represented with three fields:
	noteList -> a HashMap<Integer, ArrayList<MusicNote>> allows each note in the array list to be represented by an Integer.
                 -> each Integer is a beat where a note exists (i.e a 4 beat note starting at 0 exists at key/beat 0, 1, 2, and 3). 
	         -> must use list of notes because more than one note can be played at a single beat.
	beatsPerMeasure -> an int that represents the number of beats per measure for this model.
		        -> must be greater than 0, although 4 is a common value (reason for convenience constructor).
	tempo -> int represent the tempo of the given song to be played.    
	repeats -> ArrayList of repeats that allows the user to add numerous repeats to the piece.
	        -> each repeat must be valid.    

ModelAdapter - for Provider Implementation - Class
    This class implements both our model interface as well as the providers. Therefore, it implements all of our interface code by calling the method on the IMusicModel field. 
    However the main purpose of this class was to implement the methods of the providers code using our model methods. 

Builder - Nested Class
    This class is nested inside of MusicModel. This allows us to build a model and have access to the addNote method. The model field is what is updated to contain the new data.

ViewModel - Class
    Contains all the same fields and methods as the MusicModel class, except any code that could mutate the model (i.e. addNotes()) now throws an IllegalArgumentException. 
    This was necessary for the view as it needs data from the model but should not be allowed to change it. 

IMusicView - Interface -> Methods
    drawModel “draws” one of the four views, depending on what view class is used, with the given model’s data. 
    jumpHome and jumpEnd jump to the beginning and end of compositions respectively. 

ConsoleViewImpl - Class 
    printLowestToHighest returns a String of all the notes between (and including) the lowest and highest notes. 
	This is useful for drawing.
    existingNote returns true if the given note exists at a given beat/is hashed to the given int. The string parameter represents the given note as it should be an existing pitch/
    octave combination. 
	This is a helper for drawModel.
    isStartBeat returns true if the given note starts at the given beat (represented by the given int). The string parameter represents the given note as it should be an existing
    pitch/octave combination.  
	This is a helper for drawModel.
    drawModel updates the ap field to print the model to the console as a string. The String that includes the range of lowest to highest notes and the range of beats from 0 to the  
    last beat that has one or more notes hashed to it. It also adds an “X” every time the start of a note is at a beat and a “|” if the rest of the note (its duration) is at a beat.  
    jumpHome and jumpEnd do not do anything to the console view. 

CompositeView - Class
    Two fields needed so this view can both play the music and show a visual output:
	gui -> GuiView for visual details.
	midi -> MidiViewImpl for sound related details.
    This class has the same functionality as the gui and midi meaning notes can be added and removed, a composition can be paused, and a user can jump to the beginning or end of a 
    composition. Popups are also added if the user tries to add a note or repeat as well as if a user tries to add an invalid note or repeat. drawModel renders the gui and midi 
    views and animates the white line (initially created in paintComponent) to move as the music plays. 

GuiView - Interface -> Methods
    addKeyListener adds the keyListener created by the given controller. 
    addMouseListener adds the mouseListener created by the given controller. 
    getDisplayPanel returns the displayPanel from the frame.
	This is useful for getting and updating the data in the panel in the gui/composite.  
    addNotePopUp adds a popup JDialog. 
	This is used to allow the user to input note information. 
    addRepeatPopUp adds a popup JDialog. 
	This is used to allow the user to input repeat information.
    getOutput returns the output field. 
    addPopUpError adds a popup JDialog. 
	This pops up if the user tries to add an invalid note. 
    pauseMusic pauses the composition. 
	It pauses based on the midi which allows the composite to pause both views together. 
    getScroll() returns the the scroll field. 
    getFrameWidth() returns the width of the frame. 
    getFrameSize() returns the size of the frame as it changes. 

GuiViewImpl - Class
    This outputs a JFrame containing our JPanel, specified as a ConcreteGuiViewPanel, using drawModel. The output field contains any note information the user tries to input (and
    resets after every attempted input). The dimensions are currently fixed. drawModel calls setModel from the ConcreteGuiViewPanel class so it will draw out the given model’s data. 
    Mouse and KeyBoard listeners (created by the controller) are also added to this along with the ability to jump to the beginning and end of a composition. Popups are also added
    if the user tries to add a note or repeat as well as if a user tries to add an invalid note or repeat. Getter methods allow us to get the fields and width of objects in this 
    class.

GuiAdapter - for Provider Implementation - Class
    This class implements our GuiView interface and has the provider’s as a field. It contains all of our GuiView methods, but writes them using the providers code (or throws an
    exception if the provider had no equivalent).
	The purpose of this class was to allow us to use our mouse and key events on their code without overriding our createKeyboardHandler and createMouseHandler methods. 

ConcreteGuiViewPanel - Class -> Methods
    paintComponent creates a panel with our specifications and the model fields data (which is initialized with a ViewModel). 
	1-the list of Pitch/Octave strings are printed.
	2-the vertical lines are drawn.
	3-the horizontal lines are drawn.
	4-the rectangles for each note are drawn making the start notes pink and the remaining notes orange.
	5-any repeats once a user has added them. 
	6-the white line that is animated if the composite view is used. 
    setModel sets the model field to the given (view) model. 
    returnNoteStartBeat gets the start beat of the note at the given integer. 
	This allows us to remove notes in the gui views. It gives the y-coordinate of the note to be removed.  
    setxLine changes the x position of the white line depending on the given integer.
	This is constantly updated in the CompositeView.
    setLineInit is true if the line is in its initial position. 
    getHashPitchOctave returns the pitch/octave combo (as a string) at the given int. 
	This allows us to remove notes in the gui views. It gives the x-coordinate of the note to be removed.
    printLowestToHighest returns a String of all the notes between (and including) the lowest and highest notes. 
	This is useful for drawing.  

MidiViewImpl (Midi/Sound) - Class -> Fields
    synth -> Synthesizer that is opened and “loaded with instruments” in the constructor allowing for the correct sound to play. 
    sequencer -> is opened then used to set the tempo and hold all of the tracks of notes. This is what is eventually played. 
    tickbeat -> static constant.  

MidiViewImpl (Midi/Sound) - Class -> Methods
    pitchOctIntValue converts a given number that relates to the Midi’s note values to our Pitch and Octave values. 
    drawModel adds tracks to the sequencer and then plays the sequencer. 
	1-initlize the sequencer with a Sequence
	2-set the tempo
	3-create tracks and add notes to them. For each note a track needs to be added that tells the Sequencer when the note starts (Note_On), when it ends (Note_Off), and what 
	instrument should be played (Program_Change). This is done by adding events and messages containing the necessary note data. 
	4-set and start the sequencer. 
    repeatsFalse sets all the repeats in the given list to false. 
	This allows us to still play the repeats after the user performs an action (such as jumping home). 
    jumpHome and jumpEnd sets the tick position to the beginning and end of a composition respectively. 
    pauseMusic pauses the sequencer if pause is true. If pressed again it un-pauses the music.  
    repeatMusic resets the sequencer to the start of a repeat when it reaches the end of a repeat (the first time). 

ReveiverMock and MidiMock -> classes made specifically for testing, overwriting built in methods to check that our “program is using them correctly.”


*This allows for octaves that cannot be heard by humans. However this range was chosen because the homework states that the “pitch” should be “five characters wide.” 
    
