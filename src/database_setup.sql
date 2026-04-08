DROP TABLE IF EXISTS games;

CREATE TABLE games (
                       gameID INTEGER PRIMARY KEY,
                       title TEXT NOT NULL,
                       genre TEXT,
                       developer TEXT,
                       platform TEXT,
                       releaseYear INTEGER,
                       hoursPlayed REAL,      -- Hours spent in the game
                       comfyRating INTEGER    -- The "Cozy Rating" (1-10)
);

-- Batch Insert: 20 Cozy Samples
INSERT INTO games (gameID, title, genre, developer, platform, releaseYear, hoursPlayed, comfyRating)
VALUES (101, 'Stardew Valley', 'Farming Sim', 'ConcernedApe', 'Switch', 2016, 250.5, 10),
       (102, 'Animal Crossing', 'Social Sim', 'Nintendo', 'Switch', 2020, 415.0, 10),
       (103, 'Unpacking', 'Puzzle', 'Witch Beam', 'PC', 2021, 4.5, 9),
       (104, 'A Short Hike', 'Adventure', 'adamgryu', 'Switch', 2019, 2.0, 8),
       (105, 'Slime Rancher', 'Adventure', 'Monomi Park', 'PC', 2017, 45.3, 7),
       (106, 'Coffee Talk', 'Visual Novel', 'Toge Productions', 'Xbox', 2020, 6.0, 9),
       (107, 'Spiritfarer', 'Management', 'Thunder Lotus', 'PS4', 2020, 85.0, 10),
       (108, 'Gris', 'Platformer', 'Nomada Studio', 'PC', 2018, 3.5, 8),
       (109, 'Dorfromantik', 'Puzzle', 'Toukana Interactive', 'Switch', 2022, 12.0, 9),
       (110, 'Ooblets', 'Creature Collection', 'Glumberland', 'PC', 2022, 30.5, 8),
       (111, 'PowerWash Simulator', 'Simulation', 'FuturLab', 'PC', 2022, 60.0, 7),
       (112, 'Bear and Breakfast', 'Management', 'Gummy Cat', 'Switch', 2022, 25.5, 8),
       (113, 'Mini Motorways', 'Strategy', 'Dino Polo Club', 'PC', 2019, 15.0, 6),
       (114, 'Grow: Song of Evertree', 'World Builder', 'Prideful Sloth', 'PC', 2021, 40.0, 8),
       (115, 'Cozy Grove', 'Life Sim', 'Spry Fox', 'Switch', 2021, 110.0, 9),
       (116, 'Townscaper', 'City Builder', 'Oskar Stalberg', 'PC', 2021, 10.5, 10),
       (117, 'Lake', 'Adventure', 'Gamious', 'Xbox', 2021, 8.0, 7),
       (118, 'Alba: Wildlife Adv', 'Adventure', 'ustwo games', 'PC', 2020, 3.5, 8),
       (119, 'Tavern Master', 'Management', 'Untitled Studio', 'PC', 2021, 22.0, 7),
       (120, 'Wytchwood', 'Crafting', 'Alientrap', 'PS5', 2021, 18.5, 8);

