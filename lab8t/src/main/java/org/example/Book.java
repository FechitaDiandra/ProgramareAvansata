package org.example;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private String language;
    private LocalDate publicationDate;
    private int numberOfPages;
    private int authorId;

        public Book(int id, String title, String language, LocalDate publicationDate, int numberOfPages, int authorId) {
            this.id = id;
            this.title = title;
            this.language = language;
            this.publicationDate = publicationDate;
            this.numberOfPages = numberOfPages;
            this.authorId = authorId;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", publicationDate=" + publicationDate +
                ", numberOfPages=" + numberOfPages +
                ", authorId=" + authorId +
                '}';
    }
}


