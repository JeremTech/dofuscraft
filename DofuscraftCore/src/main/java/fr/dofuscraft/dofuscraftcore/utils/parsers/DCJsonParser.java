package fr.dofuscraft.dofuscraftcore.utils.parsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class DCJsonParser
{
    protected JsonParser jsonParser = new JsonParser();
    protected String file;
    protected InputStreamReader fileToRead;
    protected String content = "";
    protected int nbJsonElements = 0;

    public DCJsonParser(String fileToParse)
    {
        this.file = fileToParse;
    }

    public abstract ArrayList<String> getInformations(int id);

    public int getNbJsonElements()
    {
        return this.nbJsonElements;
    }

    // Fonction qui permet de savoir le nombre d'entrées du json
    protected void countJsonElements()
    {
        // On parse le contenu du fichier si le contenu n'est pas vide
        if(!this.content.isEmpty())
        {
            JsonElement jsonTree = this.jsonParser.parse(this.content);

            // On vérifie qu'en sortie du parseur on a bien du json
            if(jsonTree.isJsonObject())
            {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                this.nbJsonElements = jsonObject.size();
            }
        }

        this.nbJsonElements = 0;
    }

    // Fonction pour lire le contenu du fichier (texte brut) et le mettre dans la variable "content"
    protected void readFile()
    {
        String line = null;

        try
        {
            // On lit le fichier
            BufferedReader bufferedReader = new BufferedReader(this.fileToRead);

            // On remet à zéro la variable "content"
            this.content = "";

            // On ajoute les lignes à la suite de "content" tant qu'il y en a
            while((line = bufferedReader.readLine()) != null)
            {
                this.content += line;
            }

            // On ferme le fichier
            bufferedReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to open file 'assets/dofuscraftcore/jobs/" + file + "'");
        }
        catch (IOException e)
        {
            System.out.println("Error reading file 'assets/dofuscraftcore/jobs/" + file + "'");
        }
    }
}
