package main;

public class Assets {
        public static String[] locations = {
                        "Village",
                        "Hidden Cavern",
                        "Hideout Cavern",
                        "Nearby Village",
                        "Hideout Cavern",
                        "Secret Tunnels",
                        "The  Evil Emperor's Fortress",
                        "Hideout Cavern",
                        "Enchanted Woods",
                        "Nearby Village",
                        "Enchanted Woods",
                        "Hideout Cavern",
        };

        public static String[] combatSkills = {
                        "Swift Strike",
                        "Precision Shot",
                        "Lunge and Parry",
                        "Shield Slam",
                        "Dual Blade Technique",
                        "Fire Arrow Volley",
                        "Frost Blade",
                        "Thunderclap",
                        "Berserker Charge",
                        "Lightning Blade"
        };

        public static String[] defensiveSkills = {
                        "Evasion",
                        "Counter-Parry",
                        "Deflect",
                        "Riposte",
                        "Healing Poultice",
                        "Shield Wall",
                        "Stamina Regeneration",
                        "Guard Stance",
                        "Armored Resilience",
                        "Ironclad Defense"
        };

        public static String[] combatWeapons = {
                        "Dagger",
                        "Throwing knives",
                        "Short sword",
                        "Staff",
                        "Bow",
                        "Spear",
                        "Scimitar",
                        "War pick",
                        "Crossbow",
                        "Long sword",
                        "Battleaxe",
                        "Halberd",
                        "War hammer",
                        "Great sword",
                        "Assassin's Blade"
        };

        public static String[] defensiveEquipment = {
                        "Reinforced Helmet",
                        "Guardian Boots",
                        "Steel Gauntlets",
                        "Defender's Armguards",
                        "Iron Greaves",
                        "Warded Buckler",
                        "Enchanted Leather Armor",
                        "Mantle of Warding",
                        "Chainmail Vest",
                        "Knight's Breastplate",
                        "Dragon Scale Armor",
                        "Divine Shield",
                        "Tower of Valor",
                        "Legendary Plate Armor",
                        "Kryptonite Barrier"
        };

        public static String[] interactionsOfEnemy = {
                        "Fight",
                        "Taunt",
                        "Walked Away"
        };

        public static String[] tauntingMessages = {
                        "I've seen insects with more courage than you.\nAre you even worthy of wielding a sword?",
                        "You think you can challenge me?\nHa! You'll regret ever crossing paths with me.",
                        "You must be lost, little one.\nThis realm belongs to those stronger than you.",
                        "You dare to challenge me?\nI've broken stronger warriors than you.",
                        "Pathetic!\nYou won't last a minute against my might.",
                        "Do you even know who you're facing?\nI am the scourge of this land.",
                        "Flee now, before you suffer the consequences of your arrogance.",
                        "I'll make an example of you,\nto show others the futility of resistance.",
                        "Your bravado is amusing.\nI'll enjoy crushing you like the insignificant pest you are.",
                        "You're no hero.\nYou're just a lost soul in need of a harsh lesson."
        };

        public static String[] fellowMessages = {
                        "Ah, another adventurer!\nI hope your journey has been fruitful so far.",
                        "Greetings!\nIt's refreshing to meet a fellow wanderer in these desolate lands.",
                        "Hello!\nI'm glad to see a friendly face amidst the dangers of this realm.",
                        "Welcome, traveler.\nIt's always good to see new faces in these parts.",
                        "I've heard tales of a great hero rising.\nMany believe it's you.",
                        "Beware the northern forests,\nfor they are home to the emperor's fiercest soldiers.",
                        "I recognize you from the old prophecies.\nYour destiny is to bring peace to these lands.",
                        "The emperor's right-hand man is known to have a secret fear of fire.\nIt is said this could be useful to you.",
                        "There's an old legend about a hidden weakness in the emperor's armor.\nIt is said this could be useful to you.",
                        "The emperor's strength is vast,\nbut I've heard that he is allergic to a rare jasmine flower from the southern swamps."
        };

        public static String[] traderMessages = {
                        "Greetings, traveler!\nCare to peruse my wares?",
                        "Ah, a potential customer!\nInterested in what I have to offer?",
                        "Hello there!\nLooking to trade for something valuable, perhaps?",
                        "Good day, adventurer!\nI have goods that might pique your interest.",
                        "Welcome, welcome!\nTake a look at what I've brought for trade.",
                        "Hail, traveler!\nInterested in making a deal today?",
                        "Greetings!\nI have items that could be of great use to someone like you.",
                        "Well met, friend!\nCare to see what I have in store for you?",
                        "Hello!\nLooking for something valuable?\nYou've come to the right place.",
                        "Ah, another seeker of fortune!\nWhat can I interest you in today?"
        };

        public static String[] enemies = {
                        "Raider Leader",
                        "Rebel Commander",
                        "Marauding Bandit",
                        "Renegade General",
                        "Mercenary Captain",
                        "Outlaw Gang Leader",
                        "Border Bandit",
                        "Castle Defender",
                        "Armored Sentinel",
                        "Highway Bandit",
                        "Forest Outlaw",
                        "Mountain Brigand",
                        "Deserter Officer",
                        "Barbarian Chieftain",
                        "Rogue Knight",
                        "Highway Robber",
                        "Dark Templar",
                        "Evil Emperor's Guard",
                        "Assassin",
                        "Stalker"
        };

        public static String[] fellows = {
                        "Village Elder",
                        "Local Guide",
                        "Scholar",
                        "Fellow Rebel",
                        "Sorcerer",
                        "Bounty Hunter",
                        "Trickster",
                        "Minstrel",
                        "Tavern Keeper",
                        "Fortune Teller",
                        "Wandering Monk",
                        "Caravan Leader",
                        "Hermit",
                        "Knight Errant",
                        "Mystic",
                        "Ranger",
                        "Blacksmith",
                        "Historian",
                        "Seer",
                        "Nomad"
        };

        public static String[][] tradersAndProducts = {
                        { "Herbalist", "healers" },
                        { "Alchemist", "healers" },
                        { "Medic", "healers" },
                        { "Apothecary", "healers" },
                        { "Healer", "healers" },
                        { "Druid", "healers" },
                        { "Cleric", "healers" },
                        { "Blacksmith", "weapons" },
                        { "Weapon smith", "weapons" },
                        { "Armorer", "weapons" },
                        { "Fletcher", "weapons" },
                        { "Swordsmith", "weapons" },
                        { "Bladesmith", "weapons" },
                        { "Mercenary", "weapons" },
                        { "Merchant", "healers and weapons" },
                        { "Trader", "healers and weapons" },
                        { "Peddler", "healers and weapons" },
                        { "Vendor", "healers and weapons" },
                        { "Wandering Merchant", "healers and weapons" },
                        { "Traveling Salesman", "healers and weapons" }
        };
}
