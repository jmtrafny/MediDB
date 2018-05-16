-- MediDB Schema
-- James Trafny and Lana Adams
-- aka the best team you ever did see!
--              ^^^ @Lana, don't delete this!

-- Fresh start!
DROP DATABASE IF EXISTs MediDB;
CREATE DATABASE IF NOT EXISTS MediDB;
USE MediDB;

-- User/Patient table
-- Patients(userID, userPW, firstName, lastName, dateOfBirth, weight, gender)
CREATE TABLE Patients(
	userID	VARCHAR(50) PRIMARY KEY,
	userPW	VARCHAR(50),
	firstName VARCHAR(50),
	lastName VARCHAR(50),
	dateOfBirth DATE,
	weight FLOAT(6,2),
	gender CHAR(1)
    );

-- Medications table
-- Medications(brandName, genericName, amountMG)
CREATE TABLE Medications(
	brandName VARCHAR(50),
	genericName VARCHAR(50),
	amountMG FLOAT(6,2),
	PRIMARY KEY(brandName)
    );

-- Interactions table
-- Interactions(medication1, medication2)
CREATE TABLE Interactions(
	medication1 VARCHAR(50),
	medication2 VARCHAR(50),
	FOREIGN KEY (medication1) REFERENCES Medications(brandName) 
	ON DELETE CASCADE,
	FOREIGN KEY (medication2) REFERENCES Medications(brandName) 
	ON DELETE CASCADE
    );

-- Regimen table
-- Regimen(userID, brandName, dosage, frequency, count)
CREATE TABLE Regimen(
	userID VARCHAR(50),
	brandName VARCHAR(50),
	dosage FLOAT(6,2),
	frequency VARCHAR(3),
	count INTEGER,
	FOREIGN KEY (userID) REFERENCES Patients(userID)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (brandName) REFERENCES Medications(brandName) 
	ON DELETE CASCADE ON UPDATE CASCADE
    );
    
-- Initial Data
-- Medication
INSERT INTO medications VALUES
	('Lipitor', 'Atorvastatin Calcium', 100),
	('Synthroid', 'Levothyroxine', 100),
	('Prinivil', 'Lisinopril', 100),
	('Prilosec', 'Omeprazole', 20),
	('Glucophage', 'Metformin', 32),
	('Norvasc', 'Amlodipine', 100),
	('Zocor', 'Simvastatin', 300),
	('Lortab', 'Hydrocodone/Acetaminophen', 230),
	('Toprol XL', 'Metoprolol ER', 225),
	('Cozaar', 'Losartan', .5),
	('Zithromax', 'Azithromycin', .5),
	('Ambien', 'Zolpidem', 25),
	('Microzide', 'Hydrochlorothiazide', 225),
	('Lasix', 'Furosemide', 800),
	('Lopressor', 'Metoprolol', 25),
	('Protonix', 'Pantoprazole', 25),
	('Neurontin', 'Gabapentin', 45),
	('Amoxil', 'Amoxicillin', 800),
	('Deltasone', 'Prednisone', 250),
	('Zoloft', 'Sertraline', 100),
	('Flomax', 'Tamsulosin', 10),
	('Flonase', 'Fluticasone', 10),
	('Pravachol', 'Pravastatin', 25),
	('Ultram', 'Tramadol', 50),
	('Singulair', 'Montelukast', 100),
	('Lexapro', 'Escitalopram', 225),
	('Coreg', 'Carvedilol', 300),
	('Xanax', 'Alprazolam', 25),
	('Coumadin', 'Warfarin', 50),
	('Mobic', 'Meloxicam', 800),
	('Plavix', 'Clopidogrel', 10),
	('Augmentin XR', 'Amoxicillin/Potassium Clavulanate ER', 225),
	('Zyloprim', 'Allopurinol', 125),
	('Wellbutrin', 'Bupropion', 10),
	('Zestoretic', 'Lisinopril/HCTZ', 5),
	('Celexa', 'Citalopram', 1),
	('Tenormin', 'Atenolol', 1000),
	('Cymbalta', 'Duloxetine', 2000),
	('Prozac', 'Fluoxetine', 12.5),
	('Tricor', 'Fenofibrate', .5),
	('Effexor', 'Venlafaxine', .8),
	('Adderall', 'Amphetamine/Dextroamphetamine', 20),
	('Flexeril', 'Cyclobenzaprine', 10),
	('Medrol', 'Methylprednisolone', 25),
	('Klor-Con', 'Potassium Chloride', 50),
	('Tylenol', 'Acetaminophen', 325),
    ('Advil', 'Ibuprofin', 200);
    
 INSERT INTO Patients VALUES 
	('1337', 'pass', 'James', 'Trafny', '1988-03-24', 170.00, 'm'),
    ('1338', 'word', 'Lana', 'Adams', '1992-03-06', 170.00, 'f');   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    