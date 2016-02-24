import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
					POSInstance pi = new POSInstance(posSplit[k], wordSplit[k], pos.getName(), j+1, k);
					posMap.get(posSplit[k]).add(pi);
				}
			}
		}

		String outputPath = "posmap.txt";
		File f = new File(outputPath);
		if (f.exists()) {
			f.delete();
			f.createNewFile();
		}
		PrintWriter outFile = new PrintWriter(new FileWriter(outputPath, true));

		Iterator it = posMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey());
			outFile.println(pair.getKey());
			HashSet<POSInstance> pis = (HashSet<POSInstance>) pair.getValue();
			for (POSInstance s : pis) {
				System.out.println(s.getWord() + " | " + s.filename + " | " + s.getSentenceNumber() + " | "  + s.getTokenNumber());
				outFile.println(s.getWord() + " | " + s.filename + " | " + s.getSentenceNumber() + " | "  + s.getTokenNumber());
			}
			outFile.println();
			System.out.println();
			it.remove(); // avoids a ConcurrentModificationException
		}
		outFile.close();
	}

}
