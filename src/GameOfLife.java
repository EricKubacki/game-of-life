import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GameOfLife {
	
	private Cell cell;
	
	@Before
	public void before() {
		cell = new Cell();
	}

	@Test
	public void testCell_DiesIfNoAliveNeighborAfterEvolution() {
		cell.evolve(0);
		assertThat(cell.isAlive(), is(false));
	}
	
	@Test
	public void testCell_DiesIfAliveNeighborsIsLessThan2() {
		cell.evolve(1);
		assertThat(cell.isAlive(), is(false));
	}
	
	@Test
	public void testCell_LivesIfAliveNeighborsIs2() {
		cell.evolve(2);
		assertThat(cell.isAlive(), is(true));
	}

	@Test
	public void testCell_LivesIfAliveNeighborsIs3() {
		cell.evolve(3);
		assertThat(cell.isAlive(), is(true));
	}
	
	@Test
	public void testCell_DiesIfAliveNeighborsIsGreaterThan3() {
		cell.evolve(4);
		assertThat(cell.isAlive(), is(false));
	}

	@Test
	public void deadCell_BecomesAliveIfItHas3LiveNeighbors() {
		cell = new Cell("Dead Cell");
		cell.evolve(3);
		assertThat(cell.isAlive(), is(true));
	}

	private static class Cell {
		private boolean state;

		public Cell() {
			state = true;
		}
		
		public Cell(String state) {
			this.state = true;
			if(state.equals("Dead Cell")) {
				this.state = false;
			} 
		}
		
		public void evolve(int numberOfAliveNeighbors) {
			state = false;
			if(numberOfAliveNeighbors == 2 || numberOfAliveNeighbors ==3) {
				state = true;
			}
		}

		public boolean isAlive() {
			return state;
		}
		
	}
}