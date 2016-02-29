import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String path = "data/joey/";
		File folder = new File(path);
		File[] files = folder.listFiles();

		List<File> lemmaFiles = new ArrayList<>();
		List<File> posFiles = new ArrayList<>();
		List<File> wordFiles = new ArrayList<>();

		for (File f : files) {
			if (f.getName().contains("lemmas"))
				lemmaFiles.add(f);
			else if (f.getName().contains("tags"))
				posFiles.add(f);
			else if (f.getName().contains("words"))
				wordFiles.add(f);
		}

		HashMap<String, HashSet<POSInstance>> posMap = new HashMap<>();

		for (int i = 0; i < lemmaFiles.size(); i++) {
			File lem = lemmaFiles.get(i);
			File pos = posFiles.get(i);
			File word = wordFiles.get(i);

			List<String> lemLines = FileManager.readFile(lem);
			List<String> posLines = FileManager.readFile(pos);
			List<String> wordLines = FileManager.readFile(word);

			for (int j = 0; j < lemLines.size(); j++) {
				String[] lemSplit = lemLines.get(j).split(" ");
				String[] posSplit = posLines.get(j).split(" ");
				String[] wordSplit = wordLines.get(j).split(" ");

				for (int k = 0; k < posSplit.length; k++) {
					if (!posMap.containsKey(posSplit[k])) {
						posMap.put(posSplit[k], new HashSet<POSInstance>());
					}
					System.out.println(lem.getName() + " " + lemSplit.length + " " + posSplit.length + " "
							+ wordSplit.length + " " + (j + 1) + " " + k);
					POSInstance pi = new POSInstance(posSplit[k], wordSplit[k], lemSplit[k], pos.getName(), j + 1, k);
					posMap.get(posSplit[k]).add(pi);
				}
			}
		}

		Iterator it = posMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			HashSet<POSInstance> words = (HashSet<POSInstance>) pair.getValue();
			outputToFile((String) pair.getKey(), words);
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	private static void outputToFile(String pos, HashSet<POSInstance> words) throws IOException {
		String folder = "posmaps/";
		String outputPath = folder + pos + ".txt";
		File f = new File(outputPath);
		if (f.exists()) {
			f.delete();
			f.createNewFile();
		}

		List<POSInstance> sortedWords = new ArrayList<>(words);
		Collections.sort(sortedWords, new Comparator<POSInstance>() {
			@Override
			public int compare(POSInstance p1, POSInstance p2) {
				return p1.getWord().compareTo(p2.getWord());
			}
		});

		PrintWriter outFile = new PrintWriter(new FileWriter(outputPath, true));
		System.out.println("POS Tag: " + pos);
		outFile.println("POS Tag: " + pos);
		for (POSInstance s : sortedWords) {
			System.out.println("Word: " + s.getWord() + " | Lemma: " + s.getLemma() + " | " + s.filename + " | SN:"
					+ s.getSentenceNumber() + " | TN:" + s.getTokenNumber());
			outFile.println("Word: " + s.getWord() + " | Lemma: " + s.getLemma() + " | " + s.filename + " | SN:"
					+ s.getSentenceNumber() + " | TN:" + s.getTokenNumber());
		}
		outFile.close();
	}

}
