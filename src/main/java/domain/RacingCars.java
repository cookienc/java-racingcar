package domain;

import exception.CannotFindMaxPositionException;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RacingCars {

    public static final String CANNOT_FIND_MAX_POSITION = "최대 거리를 찾을 수 없습니다.";
    private final List<RacingCar> racingCars = new ArrayList<>();

    public RacingCars(final List<RacingCar> racingCars) {
        this.racingCars.addAll(racingCars);
    }

    public void moveAll() {
        for (RacingCar racingCar : this.racingCars) {
            racingCar.move();
        }
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinnerNames() {
        int maxPosition = findMaxPosition(this);

        return racingCars.stream()
                .filter(racingCar -> racingCar.getPosition() == maxPosition)
                .map(RacingCar::getName)
                .collect(toList());
    }

    private int findMaxPosition(final RacingCars racingCars) {
        return racingCars.getRacingCars().stream()
                .mapToInt(RacingCar::getPosition)
                .max()
                .orElseThrow(() -> new CannotFindMaxPositionException(CANNOT_FIND_MAX_POSITION));
    }
}
