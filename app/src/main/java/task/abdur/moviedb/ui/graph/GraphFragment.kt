package task.abdur.moviedb.ui.graph

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson
import com.opencsv.CSVReader
import task.abdur.moviedb.R
import task.abdur.moviedb.databinding.FragmentGraphBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Collections
import java.util.Locale

class GraphFragment : Fragment() {

    private var _binding: FragmentGraphBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val csvFilePath = "stock.csv" // Replace with the actual file path
        val csvData = readCSVData(csvFilePath)
//        createGraph(csvData)
//        configureChart(binding.spikeChart)

        // Create sample data for the chart (you can replace this with your data)
         generateData(csvData)
//        binding.spikeChart.data = data
//        binding.spikeChart.invalidate()
        return root
    }
    private fun configureChart(chart: LineChart) {
        // Configure the X-axis
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)

        // Configure the Y-axis
        val yAxisLeft = chart.axisLeft
        yAxisLeft.setDrawGridLines(false)

        val yAxisRight = chart.axisRight
        yAxisRight.isEnabled = false

        // Configure the legend
        val legend = chart.legend
        legend.isEnabled = false

        // Enable touch gestures
        chart.setTouchEnabled(true)
        chart.setPinchZoom(true)
        chart.description.isEnabled = false
    }

fun generateData(csvData: List<List<String>>){
    // Step 1: Parse the CSV data
    val timestamps = ArrayList<Long>()
    val closeValues = ArrayList<Float>()

    for (i in 1 until csvData.size) {
        val row = csvData[i]
        if (row.size >= 5) { // Make sure the row has at least 5 columns
            val timestampStr = row[0]
            val closeStr = row[4]

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
            val date = sdf.parse(timestampStr)
            val timestamp = date?.time ?: 0
            val close = closeStr.toFloatOrNull() ?: 0f

            timestamps.add(timestamp)
            closeValues.add(close)
        }
    }

    // Step 2: Create entries for the LineDataSet
    val entries = ArrayList<Entry>()
    for (i in timestamps.indices) {
        entries.add(Entry(timestamps[i].toFloat(), closeValues[i]))
    }
    // Step 3: Create a LineDataSet
    val dataSet = LineDataSet(entries, "Close Prices")

    val colorStart = resources.getColor(R.color.colorStart) // Define your start color
    val colorEnd = resources.getColor(R.color.colorEnd) // Define your end color
        // Create a gradient fill
        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, intArrayOf(colorStart, colorEnd))
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.cornerRadius = 0f // Adjust corner radius as needed

        // Set the gradient fill as the Drawable for the dataset
    dataSet.fillDrawable = gradientDrawable
    dataSet.setDrawFilled(true)
    dataSet.fillAlpha = 100

        // Customize the dataset (line color, circle color, etc.)
    dataSet.color = Color.BLUE
    dataSet.lineWidth = 2f
    dataSet.setCircleColor(Color.BLUE)
    dataSet.circleRadius = 4f
    dataSet.setDrawValues(false)
    val lineDataSets = ArrayList<ILineDataSet>()
    lineDataSets.add(dataSet)
    val lineData = LineData(lineDataSets)
    binding.spikeChart.data = lineData
    binding.spikeChart.setNoDataText("No data available")
    binding.spikeChart.setTouchEnabled(false)
    binding.spikeChart.setPinchZoom(false)
    binding.spikeChart.description = Description().apply {
        text = "Close Prices Over Time"
    }
    binding.spikeChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    binding.spikeChart.axisRight.isEnabled = false
    binding.spikeChart.data.setDrawValues(false)
    val leftAxis = binding.spikeChart.axisLeft
    leftAxis.axisMinimum = Collections.min(closeValues) - 1f
    leftAxis.axisMaximum = Collections.max(closeValues) + 1f

    binding.spikeChart.invalidate()
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    // Function to read data from a CSV file and return a list of lists (rows and columns)
    fun readCSVData(filePath: String): List<List<String>> {
        val data: MutableList<List<String>> = mutableListOf()

        try {
            // Open the CSV file using an InputStreamReader and BufferedReader
            val inputStream = requireActivity().assets.open(filePath)
            val reader = BufferedReader(InputStreamReader(inputStream))

            // Initialize a CSVReader
            val csvReader = CSVReader(reader)

            // Read each line of the CSV file
            var line: Array<String>?
            while (csvReader.readNext().also { line = it } != null) {
                // Add the line as a list of strings to the data list
                if (line != null) {
                    data.add(line!!.toList())
                }
            }

            // Close the CSVReader and the BufferedReader
            csvReader.close()
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data
    }
}