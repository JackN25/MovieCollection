import com.sun.source.tree.NewArrayTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;
    private static ArrayList<String> ACTORS = new ArrayList<String>();
    private static ArrayList<String> GENRES = new ArrayList<String>();
    private static ArrayList<Movie> ACTION_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> ADVENTURE_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> ANIMATION_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> COMEDY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> CRIME_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> DOCUMENTARY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> DRAMA_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> FAMILY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> FANTASY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> FOREIGN_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> HISTORY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> HORROR_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> MUSIC_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> MYSTERY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> ROMANCE_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> SCIFI_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> TV_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> THRILLER_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> WAR_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> WESTERN_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> top50Rated = new ArrayList<Movie>();
    private static ArrayList<Movie> top50Revenue = new ArrayList<Movie>();



    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter an actor: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        if (ACTORS.isEmpty()) {
            String allActors = "";
            for (Movie m : movies) {
                allActors += m.getCast() + "|";
            }
            String [] allActorsList = allActors.split("\\|");
            for (String actor : allActorsList) {
                if (!ACTORS.contains(actor)) {
                    ACTORS.add(actor);
                }
            }
        }

        ArrayList<String> results = new ArrayList<String>();
        for (String actor : ACTORS) {
            if (actor.toLowerCase().contains(searchTerm)) {
                results.add(actor);
            }
        }
        Collections.sort(results);
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which cast member would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedActor = results.get(choice - 1);

        ArrayList<Movie> movieResults = new ArrayList<Movie>();
        for (Movie m : movies) {
            if (m.getCast().contains(selectedActor)) {
                movieResults.add(m);
            }
        }
        sortResults(movieResults);

        for (int i = 0; i < movieResults.size(); i++)
        {
            String title = movieResults.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movieResults.get(choice2 - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keywords = movies.get(i).getKeywords();
            keywords = keywords.toLowerCase();

            if (keywords.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        if (GENRES.isEmpty()) {
            String allGenres = "";
            for (Movie m : movies) {
                allGenres += m.getGenres() + "|";
            }
            String [] allGenresList = allGenres.split("\\|");
            for (String genre : allGenresList) {
                if (!GENRES.contains(genre)) {
                    GENRES.add(genre);
                }
            }
            Collections.sort(GENRES);
        }
        for (int i = 0; i < GENRES.size(); i++)
        {
            String title = GENRES.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which genre would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Movie> moviesInGenre = new ArrayList<Movie>();
        if (ACTION_MOVIES.isEmpty()) {
            sortMoviesIntoGenres();
        }
        if (choice == 1) {
            moviesInGenre = ACTION_MOVIES;
        } else if (choice == 2) {
            moviesInGenre = ADVENTURE_MOVIES;
        } else if (choice == 3) {
            moviesInGenre = ANIMATION_MOVIES;
        } else if (choice == 4) {
            moviesInGenre = COMEDY_MOVIES;
        } else if (choice == 5) {
            moviesInGenre = CRIME_MOVIES;
        } else if (choice == 6) {
            moviesInGenre = DOCUMENTARY_MOVIES;
        } else if (choice == 7) {
            moviesInGenre = DRAMA_MOVIES;
        } else if (choice == 8) {
            moviesInGenre = FAMILY_MOVIES;
        } else if (choice == 9) {
            moviesInGenre = FANTASY_MOVIES;
        } else if (choice == 10) {
            moviesInGenre = FOREIGN_MOVIES;
        } else if (choice == 11) {
            moviesInGenre = HISTORY_MOVIES;
        } else if (choice == 12) {
            moviesInGenre = HORROR_MOVIES;
        } else if (choice == 13) {
            moviesInGenre = MUSIC_MOVIES;
        } else if (choice == 14) {
            moviesInGenre = MYSTERY_MOVIES;
        } else if (choice == 15) {
            moviesInGenre = ROMANCE_MOVIES;
        } else if (choice == 16) {
            moviesInGenre = SCIFI_MOVIES;
        } else if (choice == 17) {
            moviesInGenre = TV_MOVIES;
        } else if (choice == 18) {
            moviesInGenre = THRILLER_MOVIES;
        } else if (choice == 19) {
            moviesInGenre = WAR_MOVIES;
        } else if (choice == 20) {
            moviesInGenre = WESTERN_MOVIES;
        }

        sortResults(moviesInGenre);
        for (int i = 0; i < moviesInGenre.size(); i++)
        {
            String title = moviesInGenre.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = moviesInGenre.get(choice2 - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {
        if (top50Rated.isEmpty()) {
            top50Rated.add(movies.get(0));
            for (int i = 1; i < movies.size(); i++) {
                if (top50Rated.size() < 50 || (top50Rated.size() == 50 && movies.get(i).getUserRating() > top50Rated.get(49).getUserRating())) {
                    boolean placed = false;
                    while (!placed) {
                        for (int j = 0; j < top50Rated.size(); j++) {
                            if (movies.get(i).getUserRating() >= top50Rated.get(j).getUserRating()) {
                                top50Rated.add(j, movies.get(i));
                                placed = true;
                                if (top50Rated.size() == 51) {
                                    top50Rated.remove(50);
                                }
                                break;
                            }
                        }
                        if (!placed) {
                            top50Rated.add(movies.get(i));
                            placed = true;
                            if (top50Rated.size() == 51) {
                                top50Rated.remove(50);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < top50Rated.size(); i++)
        {
            String title = top50Rated.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ": " + top50Rated.get(choiceNum - 1).getUserRating());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = top50Rated.get(choice2 - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue()
    {
        if (top50Revenue.isEmpty()) {
            top50Revenue.add(movies.get(0));
            for (int i = 1; i < movies.size(); i++) {
                if (top50Revenue.size() < 50 || (top50Revenue.size() == 50 && movies.get(i).getRevenue() > top50Revenue.get(49).getRevenue())) {
                    boolean placed = false;
                    while (!placed) {
                        for (int j = 0; j < top50Revenue.size(); j++) {
                            if (movies.get(i).getRevenue() >= top50Revenue.get(j).getRevenue()) {
                                top50Revenue.add(j, movies.get(i));
                                placed = true;
                                break;
                            }
                        }
                        if (!placed) {
                            top50Revenue.add(movies.get(i));
                            placed = true;
                            if (top50Revenue.size() == 51) {
                                top50Revenue.remove(50);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < top50Revenue.size(); i++)
        {
            String title = top50Revenue.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title + ": " + top50Revenue.get(choiceNum - 1).getRevenue());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice2 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = top50Revenue.get(choice2 - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortMoviesIntoGenres() {
        for (Movie m : movies) {
            if (m.getGenres().contains("Action")) {
                ACTION_MOVIES.add(m);
            }
            if (m.getGenres().contains("Adventure")) {
                ADVENTURE_MOVIES.add(m);
            }if (m.getGenres().contains("Animation")) {
                ANIMATION_MOVIES.add(m);
            }if (m.getGenres().contains("Comedy")) {
                COMEDY_MOVIES.add(m);
            }if (m.getGenres().contains("Crime")) {
                CRIME_MOVIES.add(m);
            }if (m.getGenres().contains("Documentary")) {
                DOCUMENTARY_MOVIES.add(m);
            }if (m.getGenres().contains("Drama")) {
                DRAMA_MOVIES.add(m);
            }if (m.getGenres().contains("Family")) {
                FAMILY_MOVIES.add(m);
            }if (m.getGenres().contains("Fantasy")) {
                FANTASY_MOVIES.add(m);
            }if (m.getGenres().contains("Foreign")) {
                FOREIGN_MOVIES.add(m);
            }if (m.getGenres().contains("History")) {
                HISTORY_MOVIES.add(m);
            }if (m.getGenres().contains("Horror")) {
                HORROR_MOVIES.add(m);
            }if (m.getGenres().contains("Music")) {
                MUSIC_MOVIES.add(m);
            }if (m.getGenres().contains("Mystery")) {
                MYSTERY_MOVIES.add(m);
            }if (m.getGenres().contains("Romance")) {
                ROMANCE_MOVIES.add(m);
            }if (m.getGenres().contains("Science Fiction")) {
                SCIFI_MOVIES.add(m);
            }if (m.getGenres().contains("TV Movie")) {
                TV_MOVIES.add(m);
            }if (m.getGenres().contains("Thriller")) {
                THRILLER_MOVIES.add(m);
            }if (m.getGenres().contains("War")) {
                WAR_MOVIES.add(m);
            }if (m.getGenres().contains("Western")) {
                WESTERN_MOVIES.add(m);
            }
        }
    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}