My MusicEditor Model
-------------------------
My design includes a MusicEditorModel, which implements the IMusicEditor interface.
The MusicEditorModel models a song as a collection of beats which contains collections of notes.
It provides methods for editing, adding, removing and accessing notes.

The MusicEditorModel has an List<Beat> which represents all the beats in this piece.
The length of the list is the length of the song.

A Beat has a Set of Notes that occur during this beat and an ID which indicated where the beat is located
witin the song.
A Note has a pitch, an octave,=a noteType, an instrument, and a volume.
The Pitch is represents by an enum which can be one of C C♯ D D♯ E F F♯ G G♯ A A♯ B.
An octave is any integer greater than or equal to 0.
A NoteType is either a Head or a Sustain, a Head being the onset of a note and a Sustain is continuation
of that note.
An instrument is represented by a integer greater than 0 and corresponds to the index of a List of
instruments in MIDI.
A volume is represented by an integer greater than 0 and indicates the volume at which the note plays in MIDi.

----------------------------------------------------------------------------------------------------

The IMusicEditor<K> interface promises the following methods:

-insertNote(K note, int duration) which adds the given note at the end of the piece

-removeNote(K note, int start, int finish) which removes the note at the given index of the given song

-removeBeat(int beat) removes all notes in the given beat from the model

-moveNote(K note, int beat) moves the given note to the given index, only moves one beat worth of a
note

-incrementPitch(K note, int steps) changes the pitch of the indicated note by the given number of
half steps

-editPitch(K note, Pitch pitch) which changes the pitch of the given note to the given pitch

-transpose(int steps) shifts all notes in the editor by the given number of half steps

-mergeNotes(IMusicEditorModel, int startMerge) adds this model's notes to the given model's notes
starting at the given index

-getBeatPitchNums(int beat) returns a list of all PitchNumbers at the given beat

-getBeatNoteTypes(int beat) returns a list of all noteTypes at the given beat

-getPitchNums() returns a list of all the pitches in the song

-getInstruments(int beat) returns a list of all the instruments at the given beat

-getVolume(int beat) returns a list of all the volumes at the given beat

-setTempo(int tempo) sets this model's tempo to be the given tempo

-getTempo() returns this model's tempo

-printState() returns the String representation of this model's notes

-length() returns the number of beats long

-pitchNumToString(int pitchNum) returns the given pitchNum as a string
----------------------------------------------------------------------------------------------------
Design Changes
- Added Instrument and Volume fields to note to accommodate for MIDI view
- Removed Accidentals Enum and included accidentals in Pitch enum
- Made Pitch and NoteType public enums so they could be referenced outside the Note class
- Added an ID field to Beat to better keep track of beats and the notes they contain
- Changes List<Note> in Beat to Set<Note> in beat because the notes didn't have specific order within beat
- Added new methods to access beats and notes from interface
- Added tempo in music editor for MIDI view

Further Design Changes
-added the following fields to GuiViewFrame to better keep track of time and the where to scroll to
    private final JScrollPane scrollPane;
    private int width;
    private JButton enter;
    private JTextField input;
    private JPanel buttonPanel;
-added updateViewport methods to keep track of where to scroll to
-In ConcreteGuiViewPanel, reconfigured draw method to draw music editor faster by only drawing the
images that appear within the current window space.
-added the following fields to ConcreteGuiViewPanel to better keep track of time and where to scroll
  private float time;
  private int beginX;
  private int endX;
  private int beginBeat;
  private int endBeat;
  private int lowestPitch;
  private int highestPitch;
  private int noteRange;
-changes in Midi to update bpm before starting track and improved tempo.
-changes in Midi to prevent the sequencer from closing prematurely (off-by-one error fixed).
-changes in Midi to allow restarting and stopping.
----------------------------------------------------------------------------------------------------
ImmutableMusicEditorModel interface
- Used to allow views to get information from model but not edit the model.

Represents a IMusicEditorModel but provides methods for accessing information only.
Includes: getNoteType, getPitchNums, getInstruments, getVolumes, printState, numBeats, getTempo, and
pitchNumToString

Interface is implemented by ReadOnlyMusicEditor which has a IMusicEditorModel as a field.
----------------------------------------------------------------------------------------------------
IMusicView Interface promises the following
-initialize()
-setModel()

GuiView(extends IMusicView) promises these additional methods
-void StartScrollRight() which scrolls the editor to the right
-void StartScrollLeft() which scrolls the editor to the left
-void jumpToBegin() scrolls the editor to the beginning
-void jumpToEnd() scrolls the editor to the end
-void addMouseHandler(MouseHandler actionListener) adds a mouseHandler to the view
-void addKeyHandler(KeyboardHandler keyboardHandler) adds a keyHandler to the view
-String getInput() returns the input from the view as a string

MidiGuiView implements GuiView and takes in a MidiViewImpl and a GuiViewFrame
----------------------------------------------------------------------------------------------------
How to Interact With Our Composite View
-Scroll using left and right arrow keys
-Jump to beginning of the piece by hitting "b"
-Jump to end of the piece by hitting "e"
-add a note by typing in text box and hitting "enter" button:
    -Correct input: add or remove (space)
    the beat at which you want to insert (space)
    a pitch number (12 * octave) + pitch value (space)
    duration of note

 Pitch Values
 ------------
 C - (0)
 C Sharp - (1),
 D -(2)
 D Sharp - (3)
 E - (4)
 F - (5)
 F Sharp - (6)
 G - (7)
 G SHarp - (8)
 A - (9)
 A Sharp - (10)
 B - (11)

