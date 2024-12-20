package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.entities.DateUpdatable;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import by.tms.instaclone31onl.core.models.entities.BaseEntity;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class BaseRepository<T extends BaseEntity> {
    protected final CsvTable<T> csvTable;

    public BaseRepository(CsvTable<T> csvTable) {
        this.csvTable = csvTable;
    }

    protected abstract T mapper(String[] line);

    public List<T> getAllBy(Predicate<T> filter) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvTable.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            List<T> entities = new ArrayList<>();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                T obj = mapper(nextRecord);
                if (filter == null || filter.test(obj)) {
                    entities.add(obj);
                }
            }
            return entities;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public T getBy(Predicate<T> filter) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvTable.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                T obj = mapper(nextRecord);
                if (filter == null || filter.test(obj)) {
                    return obj;
                }
            }
            return null;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UUID> insert(List<T> items) {
        List<T> currentItems = getAllBy(null);
        items.forEach(x -> {
            x.setId(UUID.randomUUID());
            if (x instanceof DateUpdatable) {
                ((DateUpdatable) x).setDate(LocalDateTime.now());
            }
        });
        currentItems.addAll(items);
        write(currentItems, csvTable.getPath());
        return items.stream().map(BaseEntity::getId).toList();
    }

    public T update(T model) {
        List<T> current = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvTable.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                T obj = mapper(nextRecord);
                if (obj.getId().equals(model.getId())) {
                    if (model instanceof DateUpdatable) {
                        ((DateUpdatable) model).setDate(LocalDateTime.now());
                    }
                    current.add(model);
                    continue;
                }
                current.add(obj);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        write(current, csvTable.getPath());
        return model;
    }

    public List<UUID> delete(Predicate<T> filter) {
        List<T> current = new ArrayList<>();
        List<UUID> toDelete = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvTable.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                T obj = mapper(nextRecord);
                if (filter == null || filter.test(obj)) {
                    toDelete.add(obj.getId());
                    continue;
                }
                current.add(obj);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        write(current, csvTable.getPath());
        return toDelete;
    }

    private void write(List<T> items, String path) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(path))) {
            csvWriter.writeAll(items.stream().map(BaseEntity::getLine).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
