package com.example.hello2.Model;

import java.util.ArrayList;

public class ItemModel {
    private String ID;
    private String title;
    private String rentalType;

    private String loanType;
    private String description;

    private int copies;

    private double fee;
    private String genre;

    private String status;

    private ArrayList<ItemModel> itemList = new ArrayList<>();

    public ItemModel(String ID, String title, String genre, String rentalType, String loanType, int copies,
                     double fee, String status) {
        this.ID = ID;
        this.title = title;
        this.genre = genre;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.copies = copies;
        this.fee = fee;
        this.status = status;
    }

    public ItemModel() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }


    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ItemModel> getItemList() {
        return itemList;
    }


    @Override
    public String toString() {
        return "ID: " + ID + "\n" + "Title: " + title + "\n" + "Genre: " + genre + "\n" + "Rental Type: " + rentalType + "\n" + "Loan Type: " + loanType + "\n" + "Copies: " + copies + "\n" + "Rental Fee (USD): " + fee + "\n" + "Rental Status: " + status + "\n\n";
    }

    public String getDescription() {
        return switch (title) {
            case "Schindler's List" -> "\n" +
                    "\"Schindler's List\" is a powerful and poignant film directed by Steven Spielberg. It tells the true story of Oskar Schindler, a German businessman who saved the lives of over a thousand Jewish refugees during the Holocaust. The film explores themes of courage, sacrifice, and the triumph of the human spirit in the face of unimaginable horrors.";
            case "The Notebook" ->
                    "\"The Notebook\" is a heartfelt romantic drama that tells the story of Noah and Allie, a young couple who fall deeply in love during the summer of 1940. Their passionate and tumultuous love affair is beautifully captured in this emotional film adaptation of Nicholas Sparks' bestselling novel. With stunning performances and a timeless love story, \"The Notebook\" explores the enduring power of love and the sacrifices one is willing to make for it.";
            case "Inception" ->
                    "\"Inception\" is a mind-bending science fiction thriller directed by Christopher Nolan. The film follows the story of Dom Cobb, a skilled thief who possesses the rare ability to enter people's dreams and steal their valuable secrets. In this complex and visually stunning film, Cobb is tasked with a challenging mission: instead of stealing information, he must plant an idea in the mind of a target. As the boundaries between dreams and reality blur, Cobb and his team face mind-boggling challenges and encounter unexpected dangers. \"Inception\" is a gripping exploration of the power of dreams and the nature of reality, keeping audiences on the edge of their seats until the very end.";
            case "The Sixth Sense" ->
                    "\"The Sixth Sense\" is a supernatural horror film directed by M. Night Shyamalan. The movie revolves around the story of a young boy named Cole Sear, who possesses the ability to see and communicate with the dead. Driven by his unique gift and haunted by disturbing visions, Cole is hesitant to share his secret with anyone until he crosses paths with child psychologist Dr. Malcolm Crowe. As Dr. Crowe tries to help Cole, he unravels a chilling mystery and discovers the truth about Cole's extraordinary abilities. \"The Sixth Sense\" is renowned for its suspenseful atmosphere, unexpected plot twists, and memorable line, \"I see dead people.\" This critically acclaimed film leaves audiences captivated and questioning reality until its haunting conclusion.";
            case "Overwatch" ->
                    "\"Overwatch\" is a popular team-based first-person shooter game developed by Blizzard Entertainment. Set in a futuristic world, players choose from a diverse roster of heroes, each with unique abilities and playstyles, and work together in teams to complete various objective-based game modes. With its fast-paced gameplay, strategic teamwork, and visually stunning graphics, \"Overwatch\" offers an exhilarating experience that keeps players engaged and constantly striving for victory. Whether you prefer offense, defense, tanking, or support, \"Overwatch\" provides endless hours of intense and competitive multiplayer action.";
            case "The Last of Us" ->
                    "\"The Last of Us\" is an acclaimed action-adventure game developed by Naughty Dog. Set in a post-apocalyptic world overrun by infected creatures, players assume the roles of Joel, a hardened survivor, and Ellie, a young girl with a mysterious immunity to the infection. Together, they embark on a perilous journey across the United States, facing dangerous encounters, engaging in intense combat, and navigating emotional challenges. The game features a gripping storyline, immersive gameplay mechanics, and stunning visuals, all of which contribute to a captivating and emotionally charged gaming experience. \"The Last of Us\" is praised for its deep character development, thought-provoking narrative, and impactful storytelling that explores themes of survival, loyalty, and the lengths one would go to protect those they care about.";
            case "RDR" ->
                    "\"Red Dead Redemption\" is an open-world action-adventure game developed by Rockstar Games. Set in the American Wild West at the turn of the 20th century, the game follows the story of John Marston, a former outlaw seeking redemption. Players traverse a vast and immersive landscape, filled with towns, deserts, and wilderness, as they embark on a quest to track down members of Marston's former gang and confront his troubled past. Along the way, players engage in thrilling gunfights, undertake challenging missions, and interact with a diverse cast of characters in a morally complex world. With its stunning visuals, rich storytelling, and immersive gameplay mechanics, \"Red Dead Redemption\" offers players an unforgettable journey through the rugged and untamed frontier.";
            case "IT" ->
                    "\"IT\" is a horror novel written by Stephen King and later adapted into a film. The story revolves around a group of friends known as the Losers' Club, who come face to face with a malevolent entity that takes the form of Pennywise the Dancing Clown. Set in the town of Derry, Maine, the group of children must confront their deepest fears and unravel the dark secrets of their town to defeat the ancient evil that preys upon their fears. With its chilling atmosphere, suspenseful storytelling, and memorable characters, \"IT\" explores themes of childhood trauma, friendship, and the power of facing one's fears. Whether in its written form or on the screen, \"IT\" is a captivating and terrifying tale that has become a staple in the horror genre.";
            case "Fight Club" ->
                    "\"Fight Club\" is a provocative novel written by Chuck Palahniuk and adapted into a film directed by David Fincher. The story follows an unnamed narrator, disillusioned with his mundane existence, who forms an underground fight club with the charismatic and anarchistic Tyler Durden. The club serves as an outlet for men seeking an escape from societal expectations and a way to reclaim their masculinity. As the club gains popularity, it evolves into a violent and anarchic movement that challenges consumerism, conformity, and the perceived emptiness of modern life. Blurring the lines between reality and fantasy, \"Fight Club\" delves into themes of identity, masculinity, rebellion, and the destructive nature of unchecked ideology. It is a thought-provoking exploration of the human condition and the search for meaning in a world that often feels disconnected and hollow.";
            case "The Dark Knight" ->
                    "\"The Dark Knight\" is a critically acclaimed superhero film directed by Christopher Nolan and serves as the second installment in his Batman film trilogy. The story follows Batman, played by Christian Bale, as he faces one of his most formidable adversaries, the Joker, portrayed by Heath Ledger in an iconic performance. Set in the gritty and realistic world established in Nolan's films, \"The Dark Knight\" explores the themes of chaos, morality, and the thin line between heroism and vigilantism.";
            case "Blade Runner" ->
                    "\"Blade Runner\" (1982) is a sci-fi film directed by Ridley Scott. Set in a dystopian future, it follows a retired blade runner tasked with hunting down rogue androids called replicants. The film explores themes of identity, humanity, and the moral implications of artificial intelligence.";
            case "Easy Rider" -> "\n" +
                    "\"Easy Rider\" (1969) is a countercultural road movie directed by Dennis Hopper. It tells the story of two bikers who embark on a cross-country motorcycle trip, encountering various characters and exploring themes of freedom, rebellion, and the changing social landscape of America in the 1960s. The film became an iconic representation of the counterculture movement and is known for its memorable soundtrack and unconventional storytelling style.";
            case "Star Wars" ->
                    "\"Star Wars\" (1977) is a groundbreaking science fiction film directed by George Lucas. It takes place in a galaxy far, far away and follows the epic battle between the Rebel Alliance and the tyrannical Galactic Empire. The film introduced audiences to iconic characters like Luke Skywalker, Princess Leia, Han Solo, and Darth Vader. With its imaginative world-building, thrilling space battles, and unforgettable story, \"Star Wars\" revolutionized the film industry and became a beloved cultural phenomenon that spawned a successful franchise. May the Force be with you!";
            case "Casablanca" ->
                    "\"Casablanca\" (1942) is a classic romantic drama set during World War II. Directed by Michael Curtiz, the film stars Humphrey Bogart and Ingrid Bergman in a timeless tale of love, sacrifice, and redemption. Set in the Moroccan city of Casablanca, the story follows Rick Blaine, a cynical American expatriate who must make difficult choices when his former lover, Ilsa Lund, reenters his life with her husband, Victor Laszlo, a renowned resistance leader. Filled with memorable quotes, atmospheric settings, and unforgettable performances, \"Casablanca\" remains one of the greatest films in cinematic history, capturing the essence of love and honor in a time of turmoil.";
            case "Gone with the Wind" ->
                    "\"Gone with the Wind\" (1939) is an epic historical romance film based on Margaret Mitchell's novel of the same name. Directed by Victor Fleming, the film stars Vivien Leigh and Clark Gable and is set against the backdrop of the American Civil War and Reconstruction era. The story follows Scarlett O'Hara, a strong-willed Southern belle, as she navigates love, loss, and the changing social and political landscape of the South. With its sweeping cinematography, iconic costumes, and powerful performances, \"Gone with the Wind\" remains a monumental piece of cinematic history, exploring themes of resilience, passion, and the enduring human spirit.";
            default -> "Description not available";
        };
    }

}


