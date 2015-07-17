import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class SoundPlayer
{
	private int [] notes = {80, 50, 60, 80};
	private int [] instruments = {113/*verde*/, 114/*rosa sem verde*/, 114/*rosa com verde*/, 86/*azul*/};
	private Sequencer sequencer;
	private Sequence sequence;
	private Track track;
	private ShortMessage first;
	
	public SoundPlayer()
	{
		try
		{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			first = new ShortMessage();
		}
		catch (Exception ex){}
	}
	
	public void playSound(int soundType)
	{
		try {
			sequence = new Sequence(Sequence.PPQ, 4);
    		track = sequence.createTrack();
    		
    		first.setMessage(192, 1, instruments[soundType-1], 0);
    		MidiEvent changeInstrument = new MidiEvent(first, 1);
			track.add(changeInstrument);
			
    		track.add(makeEvent(144, 1, notes[soundType-1], 100, 1));
    		track.add(makeEvent(128, 1, notes[soundType-1], 100, 5));
    		
    		sequencer.setSequence(sequence);
    		sequencer.start();
		}
		catch (Exception ex){}
	}
	
	public void cheer()
	{
		try
		{
			sequence = new Sequence(Sequence.PPQ, 4);
    		track = sequence.createTrack();
    		
    		first.setMessage(192, 1, 126, 0);
    		MidiEvent changeInstrument = new MidiEvent(first, 1);
			track.add(changeInstrument);
			
    		track.add(makeEvent(144, 1, 60, 100, 1));
    		track.add(makeEvent(128, 1, 60, 100, 12));
    		
    		sequencer.setSequence(sequence);
    		sequencer.start();
		}
		catch(Exception ex){}
	}
	
	public MidiEvent makeEvent(int cmd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(cmd, chan, one, two);
			event = new MidiEvent(a, tick);
		}
		catch (Exception e) {}
		return event;
	}
}
