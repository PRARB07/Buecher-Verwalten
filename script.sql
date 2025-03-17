CREATE DATABASE Buecher_Verwalten;

CREATE TABLE Genre (
    GenreID INT AUTO_INCREMENT PRIMARY KEY,
    Genre VARCHAR(50)
);

CREATE TABLE Kunde (
    KundenID INT AUTO_INCREMENT PRIMARY KEY, 
    Vorname VARCHAR(50),
    Nachname VARCHAR(50),
    Ort VARCHAR(50),
    Straße VARCHAR(50),
    PLZ VARCHAR(5)
);

CREATE TABLE Ausleihung (
    AusleihungID INT AUTO_INCREMENT PRIMARY KEY,
    Ausleihdatum DATETIME,
    KundenID INT,
    FOREIGN KEY (KundenID) REFERENCES Kunde(KundenID)
);

CREATE TABLE Buch (
    BuchID INT AUTO_INCREMENT PRIMARY KEY,
    Titel VARCHAR(255),
    Autor VARCHAR(255),
    Erscheinungsdatum DATE,
    Beschreibung TEXT,
    AusleihungID INT,
    FOREIGN KEY (AusleihungID) REFERENCES Ausleihung(AusleihungID)
);

CREATE TABLE Genre2Buch (
    BuchID INT,
    GenreID INT,
    FOREIGN KEY (BuchID) REFERENCES Buch(BuchID),
    FOREIGN KEY (GenreID) REFERENCES Genre(GenreID)
);
