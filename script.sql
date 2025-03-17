CREATE DATABASE Buecher_Verwalten;

CREATE TABLE Genre (
    GenreID INT NOT NULL,
    Genre VARCHAR(50),
    PRIMARY KEY (GenreID)
);

CREATE TABLE Kunde (
    KundenID INT NOT NULL, 
    Vorname VARCHAR(50),
    Nachname VARCHAR(50),
    Ort VARCHAR(50),
    Straße VARCHAR(50),
    PLZ VARCHAR(5),
    PRIMARY KEY (KundenID)
);

CREATE TABLE Ausleihung (
    AusleihungID INT NOT NULL,
    Ausleihdatum DATETIME,
    KundenID INT,
    PRIMARY KEY (AusleihungID),
    FOREIGN KEY (KundenID) REFERENCES Kunde(KundenID)
);

CREATE TABLE Buch (
    BuchID INT NOT NULL,
    Titel VARCHAR(255),
    Autor VARCHAR(255),
    Erscheinungsdatum DATE,
    Beschreibung TEXT,
    AusleihungID INT,
    PRIMARY KEY(BuchID),
    FOREIGN KEY (AusleihungID) REFERENCES Ausleihung(AusleihungID)
);

CREATE TABLE Genre2Buch (
    BuchID INT,
    GenreID INT,
    FOREIGN KEY (BuchID) REFERENCES Buch(BuchID),
    FOREIGN KEY (GenreID) REFERENCES Genre(GenreID)
);
