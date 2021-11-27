import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class thread_for_Un extends Thread{
	private int start = 3;
	private int maxN = 3;
	private String file_location;
	private int number_completed;
	private boolean done;
	private long time_to_finish;
	private int num_concurrent_threads = 1;

	public void set_start(int start){
		this.start = start;
	}

	public void set_maxN(int maxN){
		this.maxN = maxN;
	}

	public void set_file_location(String file_location){
		this.file_location = file_location;
	}

	public void set_num_concurrent_threads(int num_concurrent_threads){
		this.num_concurrent_threads = num_concurrent_threads;
	}

	public boolean get_done(){
		return this.done;
	}

	public long get_time_to_finish(){
		return this.time_to_finish;
	}

	public double get_percent_done(){
		return (double)(10000 * this.number_completed) / (double)(this.maxN * this.num_concurrent_threads);
	}

	public void run(){
		try{
			/*
				get a starting time, to compare against later, to calculate the thread's processing time
			*/
			long start_time = java.lang.System.currentTimeMillis();
			this.done = false;
			this.number_completed = 0;
			/*
				create the file that this thread will write to, and throw an exception if the file already exists
			*/
			File storage_file = new File(this.file_location);
			if(storage_file.createNewFile()){
				System.out.printf("File created: %s%n", storage_file.getName());
			}else{
				throw new IOException("Will not overwrite a file that already exists!");
			}
			/*
				create an output stream to be used later when saving information to the file
			*/
			FileWriter output_file = new FileWriter(file_location);
			/*
				now go and do the things, starting at U(start), and skipping <num_concurrent_threads> in between each U(n)
			*/
			for(int i = start; i < maxN; i += this.num_concurrent_threads){
				Un test = new Un(i);
				output_file.write(String.format("%s%n%n%n", test.get_details()));
				this.number_completed++;
			}
			/*
				now do the last few things that need done
			*/
			this.time_to_finish = java.lang.System.currentTimeMillis() - start_time;
			output_file.close();
		}catch(Exception e){
			System.out.println(e);
		}finally{
			this.done = true;
		}
	}
}